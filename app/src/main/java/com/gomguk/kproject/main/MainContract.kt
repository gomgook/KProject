package com.gomguk.kproject.main

import com.gomguk.kproject.util.model.DataWrapper
import com.gomguk.kproject.util.model.Document

interface MainContract {
    interface Model {
        interface MainRepositoryListener {
            fun onResult(data: DataWrapper, isAdd: Boolean)
        }

        fun getData(isAdd: Boolean, listener: MainRepositoryListener)
    }

    interface View {
        fun setAdapterData(data: List<Document>, isAdd: Boolean)
    }

    interface Presenter {
        fun loadData(isAdd: Boolean)

        fun isLoading(): Boolean
    }
}