package com.gomguk.kproject.util

import java.lang.StringBuilder

class StringUtil {
    companion object {
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