package com.gomguk.kproject.content

import android.content.Intent
import com.gomguk.kproject.util.model.Document

interface ContentContract {
    interface View

    interface Presenter {
        fun getData(intent: Intent): Document
    }
}