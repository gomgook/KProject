package com.gomguk.kproject.main

import com.gomguk.kproject.base.BasePresenter
import com.gomguk.kproject.base.BaseView

interface MainContract {
    interface View: BaseView<MainPresenter>

    interface Presenter: BasePresenter
}