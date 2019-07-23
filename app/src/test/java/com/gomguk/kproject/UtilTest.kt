package com.gomguk.kproject

import org.junit.Test

import org.junit.Assert.*
import com.gomguk.kproject.util.StringUtil

class UtilTest {
    @Test
    fun checkGetParamsStr() {
        val params = HashMap<String, String>()

        params["abc"] = "111"
        params["ppp"] = "333"
        params["adadad"] = "2323323"

        assertEquals("?ppp=333&abc=111&adadad=2323323", StringUtil.getParamsStr(params))
    }
}
