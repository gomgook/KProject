package com.gomguk.kproject.main

import android.util.Log
import com.gomguk.kproject.util.Constants.Companion.API_AUTHORIZATION_TOKEN
import com.gomguk.kproject.util.Constants.Companion.API_HEADER_AUTHORIZATION
import com.gomguk.kproject.util.Constants.Companion.API_PARAM_KEYWORD
import com.gomguk.kproject.util.Constants.Companion.API_URL
import com.gomguk.kproject.util.Constants.Companion.SEARCH_KEYWORD
import com.gomguk.kproject.util.Constants.Companion.TIMEOUT
import com.gomguk.kproject.util.NetworkConnection
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class MainRepository {
    fun getData() {
        val params = HashMap<String, String>()

        try {
            params[API_PARAM_KEYWORD] = URLEncoder.encode(SEARCH_KEYWORD, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        val headers = HashMap<String, String>()

        headers[API_HEADER_AUTHORIZATION] = API_AUTHORIZATION_TOKEN

        NetworkConnection.connect(
            listener = object : NetworkConnection.NetworkConnectionListener {
                override fun onPostExecute(result: String?) {
                    Log.e("TEST", "onPostExecute: $result")
                }
            },
            url = API_URL,
            params = params,
            timeOut = TIMEOUT,
            requestType = NetworkConnection.RequestType.GET,
            headers = headers,
            requestBody = null,
            requestBodyType = null)
    }

    companion object {
        private var INSTANCE: MainRepository? = null

        fun getInstance(): MainRepository {
            return INSTANCE ?: MainRepository().apply { INSTANCE = this }
        }
    }
}