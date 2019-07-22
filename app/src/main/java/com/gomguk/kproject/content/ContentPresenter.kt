package com.gomguk.kproject.content

import android.content.Intent
import com.gomguk.kproject.util.model.Document

class ContentPresenter(private val repository: ContentRepository, val view: ContentContract.View) :
    ContentContract.Presenter {
    fun getData(intent: Intent): Document {
        return repository.getData(intent)
    }
}