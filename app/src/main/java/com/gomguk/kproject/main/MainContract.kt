package com.gomguk.kproject.main

import com.gomguk.kproject.util.model.Document

interface MainContract {
    interface View {
        fun setAdapterData(data: List<Document>, isAdd: Boolean)
    }

    interface Presenter {
        fun loadData(isAdd: Boolean)

        fun isLoading(): Boolean
    }
}