package com.gomguk.kproject.util

import java.lang.StringBuilder

class StringUtil {
    companion object {
        /**
         * parameter들을 받아 url 형식에 맞도록 이어붙인 String을 반환한다.
         */
        fun getParamsStr(params: HashMap<String, String>): String {
            val stringBuilder = StringBuilder()

            stringBuilder.append("?")

            params.forEach {
                stringBuilder.append(it.key + "=" + it.value + "&")
            }

            stringBuilder.delete(stringBuilder.lastIndex, stringBuilder.lastIndex + 1)

            return stringBuilder.toString()
        }
    }
}