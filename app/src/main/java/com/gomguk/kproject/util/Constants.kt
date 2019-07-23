package com.gomguk.kproject.util

class Constants {
    companion object {

        // Connection에 필요한 parameter.
        const val CONNECTION_PARAM_URL = "url"
        const val CONNECTION_PARAM_PARAMS = "params"
        const val CONNECTION_PARAM_TIMEOUT = "timeout"
        const val CONNECTION_PARAM_REQUEST_TYPE = "request_type"
        const val CONNECTION_PARAM_HEADERS = "headers"
        const val CONNECTION_PARAM_REQUEST_BODY = "request_body"
        const val CONNECTION_PARAM_REQUEST_BODY_TYPE = "request_body_type"

        // Error messages.
        const val ERROR_EMPTY_URL = "Url is empty."
        const val ERROR_TIMEOUT_MISSING = "Timeout invalid. Timeout should be over 0."
        const val ERROR_REQUEST_TYPE_NOT_SUPPORTED = "Not supported REST request type. We support GET, POST, PUT, DELETE only."
        const val ERROR_SCHEME_NOT_SUPPORTED = "Not supported scheme. We support HTTP or HTTPS only."
        const val ERROR_RESPONSE_CODE_ERROR = "The response code is not 200. requestCode:"

        // search api에 사용될 search keyword와 한 번에 불러올 최대 page size
        const val SEARCH_KEYWORD = "스타워즈"
        const val PAGE_SIZE = 10

        // api call에 필요한 parameter.
        const val API_PARAM_KEYWORD = "query"
        const val API_PARAM_PAGE = "page"

        const val API_HEADER_AUTHORIZATION = "Authorization"

        const val API_URL = "https://dapi.kakao.com/v3/search/book"
        const val API_AUTHORIZATION_TOKEN = "KakaoAK 23ca94e613e3e79350430d40dab794f1"

        const val TIMEOUT = 2000
    }
}