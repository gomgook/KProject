package com.gomguk.kproject.util

import android.os.AsyncTask
import com.gomguk.kproject.util.Constants.Companion.CONNECTION_PARAM_HEADERS
import com.gomguk.kproject.util.Constants.Companion.CONNECTION_PARAM_PARAMS
import com.gomguk.kproject.util.Constants.Companion.CONNECTION_PARAM_REQUEST_BODY
import com.gomguk.kproject.util.Constants.Companion.CONNECTION_PARAM_REQUEST_BODY_TYPE
import com.gomguk.kproject.util.Constants.Companion.CONNECTION_PARAM_REQUEST_TYPE
import com.gomguk.kproject.util.Constants.Companion.CONNECTION_PARAM_TIMEOUT
import com.gomguk.kproject.util.Constants.Companion.CONNECTION_PARAM_URL
import com.gomguk.kproject.util.Constants.Companion.ERROR_EMPTY_URL
import com.gomguk.kproject.util.Constants.Companion.ERROR_REQUEST_TYPE_NOT_SUPPORTED
import com.gomguk.kproject.util.Constants.Companion.ERROR_RESPONSE_CODE_ERROR
import com.gomguk.kproject.util.Constants.Companion.ERROR_SCHEME_NOT_SUPPORTED
import com.gomguk.kproject.util.Constants.Companion.ERROR_TIMEOUT_MISSING
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.nio.charset.Charset
import javax.net.ssl.HttpsURLConnection

class NetworkConnection(private val listener: NetworkConnectionListener?) : AsyncTask<HashMap<*, *>, Any, String>() {
    interface NetworkConnectionListener {
        fun onPostExecute(result: String?)
    }

    fun execute(url: String,
                params: HashMap<String, String>?,
                timeOut: Int,
                requestType: RequestType?,
                headers: HashMap<String, String>?,
                requestBody: String?,
                requestBodyType: String?) {
        val connectionParams = HashMap<String, Any?>()

        connectionParams[CONNECTION_PARAM_URL] = url
        connectionParams[CONNECTION_PARAM_PARAMS] = params
        connectionParams[CONNECTION_PARAM_TIMEOUT] = timeOut
        connectionParams[CONNECTION_PARAM_REQUEST_TYPE] = requestType
        connectionParams[CONNECTION_PARAM_HEADERS] = headers
        connectionParams[CONNECTION_PARAM_REQUEST_BODY] = requestBody
        connectionParams[CONNECTION_PARAM_REQUEST_BODY_TYPE] = requestBodyType

        this.execute(connectionParams)
    }

    override fun doInBackground(vararg params: HashMap<*, *>?): String {
        val requestParams = params[0] as HashMap<String, Any?>
        val urlStr = requestParams[CONNECTION_PARAM_URL] as String?

        urlStr?.let {
            if (urlStr.isBlank()) throw Exception(ERROR_EMPTY_URL)

            val urlStrSb = StringBuilder(it)

            requestParams[CONNECTION_PARAM_PARAMS]?.let { params ->
                urlStrSb.append(StringUtil.getParamsStr(params as HashMap<String, String>))
            }

            val timeOut = requestParams[CONNECTION_PARAM_TIMEOUT] as Int?
                    ?: throw Exception(ERROR_TIMEOUT_MISSING)
            if (timeOut < 0) {
                throw Exception(ERROR_TIMEOUT_MISSING)
            }

            val requestType = requestParams[CONNECTION_PARAM_REQUEST_TYPE]
                    ?: throw Exception(ERROR_REQUEST_TYPE_NOT_SUPPORTED)

            val url = URL(urlStrSb.toString())
            val conn = url.openConnection()
            val stringBuilder = StringBuilder()

            conn.connectTimeout = timeOut

            requestParams[CONNECTION_PARAM_HEADERS]?.let { headers ->
                setRequestHeader(conn, headers as HashMap<String, String>)
            }

            if (requestParams[CONNECTION_PARAM_REQUEST_BODY] != null
                    && requestParams[CONNECTION_PARAM_REQUEST_BODY_TYPE] != null) {
                val requestBody: String = requestParams[CONNECTION_PARAM_REQUEST_BODY] as String
                val requestBodyType: String = requestParams[CONNECTION_PARAM_REQUEST_BODY_TYPE] as String

                conn.addRequestProperty("content_type", requestBodyType)
                conn.doOutput = true
                val outputStream = conn.getOutputStream()

                outputStream.write(requestBody.toByteArray(Charset.forName("UTF-8")))
                outputStream.close()
            }

            val schemeType = checkSchemeType(it)

            if (schemeType == SchemeType.HTTP) {
                val httpConn = conn as HttpURLConnection

                httpConn.requestMethod = requestType.toString()

                val responseCode = httpConn.responseCode

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    val bufferedReader = BufferedReader(InputStreamReader(httpConn.inputStream))
                    var result: String?

                    while (true) {
                        result = bufferedReader.readLine()

                        if (result == null) {
                            break
                        }
                        stringBuilder.append(result).append("\n")
                    }
                    bufferedReader.close()
                    httpConn.disconnect()
                } else {
                    throw Exception("$ERROR_RESPONSE_CODE_ERROR$responseCode")
                }
            } else if (schemeType == SchemeType.HTTPS) {
                val httpsConn = conn as HttpsURLConnection

                httpsConn.requestMethod = requestType.toString()

                val responseCode = httpsConn.responseCode

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    val bufferedReader = BufferedReader(InputStreamReader(httpsConn.inputStream))
                    var result: String?

                    while (true) {
                        result = bufferedReader.readLine()

                        if (result == null) {
                            break
                        }
                        stringBuilder.append(result).append("\n")
                    }
                    bufferedReader.close()
                    httpsConn.disconnect()
                } else {
                    throw Exception("$ERROR_RESPONSE_CODE_ERROR$responseCode")
                }
            } else {
                throw Exception(ERROR_SCHEME_NOT_SUPPORTED)
            }

            return stringBuilder.toString()
        }

        return ""
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        listener?.onPostExecute(result)
    }

    private fun setRequestHeader(conn: URLConnection, headers: HashMap<String, String>) {
        headers.forEach {
            conn.addRequestProperty(it.key, it.value)
        }
    }

    private fun checkSchemeType(string: String): SchemeType {
        return when {
            string.startsWith("http://") -> SchemeType.HTTP
            string.startsWith("https://") -> SchemeType.HTTPS
            else -> SchemeType.NONE
        }
    }

    companion object {
        fun connect(listener: NetworkConnectionListener,
                    url: String,
                    params: HashMap<String, String>?,
                    timeOut: Int,
                    requestType: RequestType?,
                    headers: HashMap<String, String>?,
                    requestBody: String?,
                    requestBodyType: String?) {
            val networkConnection = NetworkConnection(listener)

            networkConnection.execute(
                    url = url,
                    params = params,
                    timeOut = timeOut,
                    requestType = requestType,
                    headers = headers,
                    requestBody = requestBody,
                    requestBodyType = requestBodyType)
        }
    }

    enum class SchemeType {
        HTTP, HTTPS, NONE;
    }

    enum class RequestType {
        GET, POST, PUT, DELETE
    }
}