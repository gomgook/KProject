package com.gomguk.kproject.main

import android.util.Log
import com.gomguk.kproject.util.NetworkConnection
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class MainRepository {
    fun getData() {
        val params = HashMap<String, String>()

        try {
            params[API_PARAM_KEYWORD] = URLEncoder.encode("starwars", "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        val headers = HashMap<String, String>()

        headers["Authorization"] = "KakaoAK 23ca94e613e3e79350430d40dab794f1"

        NetworkConnection.connect(
            listener = object : NetworkConnection.NetworkConnectionListener {
                override fun onPostExecute(result: String?) {
                    Log.e("TEST", "onPostExecute: $result")
                }
            },
            url = API_URL,
            params = params,
            timeOut = 2000,
            requestType = NetworkConnection.RequestType.GET,
            headers = headers,
            requestBody = null,
            requestBodyType = null)
    }

    companion object {
        private const val API_PARAM_KEYWORD = "query"

        private const val API_URL = "https://dapi.kakao.com/v3/search/book"

        private var INSTANCE: MainRepository? = null

        fun getInstance(): MainRepository {
            return INSTANCE ?: MainRepository().apply { INSTANCE = this }
        }
    }
}