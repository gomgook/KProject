package com.gomguk.kproject.main

import com.gomguk.kproject.util.Constants.Companion.API_AUTHORIZATION_TOKEN
import com.gomguk.kproject.util.Constants.Companion.API_HEADER_AUTHORIZATION
import com.gomguk.kproject.util.Constants.Companion.API_PARAM_KEYWORD
import com.gomguk.kproject.util.Constants.Companion.API_PARAM_PAGE
import com.gomguk.kproject.util.Constants.Companion.API_URL
import com.gomguk.kproject.util.Constants.Companion.SEARCH_KEYWORD
import com.gomguk.kproject.util.Constants.Companion.TIMEOUT
import com.gomguk.kproject.util.NetworkConnection
import com.gomguk.kproject.util.model.DataWrapper
import com.google.gson.Gson
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

// Koin DI는 Activity에서만 적용이 가능하므로, Activity 외의 class에서 사용하기 위해서는 KoinComponent를 implement할 필요가 있다.
class MainRepository: MainContract.Model, KoinComponent {

    // Paging 처리를 위한 values.
    private var currentPage = 1
    private var isPageEnd = false

    private val gSon: Gson by inject()

    override fun getData(isAdd: Boolean, listener: MainContract.Model.MainRepositoryListener) {
        if (isAdd && isPageEnd) return
        if (!isAdd) currentPage = 1

        val params = HashMap<String, String>()
        try {
            params[API_PARAM_KEYWORD] = URLEncoder.encode(SEARCH_KEYWORD, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        if (isAdd && !isPageEnd) {
            currentPage++
        }
        params[API_PARAM_PAGE] = currentPage.toString()

        val headers = HashMap<String, String>()
        headers[API_HEADER_AUTHORIZATION] = API_AUTHORIZATION_TOKEN

        NetworkConnection.connect(
            listener = object : NetworkConnection.NetworkConnectionListener {
                override fun onPostExecute(result: String?) {
                    val data = gSon.fromJson(result, DataWrapper::class.java)
                    isPageEnd = data.meta.is_end

                    listener.onResult(data, isAdd)
                }
            },
            url = API_URL,
            params = params,
            timeOut = TIMEOUT,
            requestType = NetworkConnection.RequestType.GET,
            headers = headers,
            requestBody = null,
            requestBodyType = null
        )
    }

    companion object {
        private var INSTANCE: MainRepository? = null

        fun getInstance(): MainRepository {
            return INSTANCE ?: MainRepository().apply { INSTANCE = this }
        }
    }
}