package com.gomguk.kproject.main

import com.gomguk.kproject.base.BasePresenter
import com.gomguk.kproject.base.BaseView
import com.gomguk.kproject.util.model.Document

interface MainContract {
    interface View: BaseView<MainPresenter> {
        fun setAdapterData(data: List<Document>)
    }

    interface Presenter: BasePresenter
}