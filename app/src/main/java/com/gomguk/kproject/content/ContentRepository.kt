package com.gomguk.kproject.content

class ContentRepository {
    companion object {
        private var INSTANCE: ContentRepository? = null

        fun getInstance(): ContentRepository {
            return INSTANCE ?: ContentRepository().apply { INSTANCE = this }
        }
    }
}