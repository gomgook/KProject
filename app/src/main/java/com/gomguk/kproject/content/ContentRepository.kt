package com.gomguk.kproject.content

import android.content.Intent
import com.gomguk.kproject.util.model.Document

class ContentRepository {
    fun getData(intent: Intent): Document {
        return intent.getParcelableExtra("data")
    }

    companion object {
        private var INSTANCE: ContentRepository? = null

        fun getInstance(): ContentRepository {
            return INSTANCE ?: ContentRepository().apply { INSTANCE = this }
        }
    }
}