package com.gomguk.kproject.content

import com.gomguk.kproject.base.BasePresenter
import com.gomguk.kproject.base.BaseView

interface ContentContract {
    interface View: BaseView<ContentPresenter>

    interface Presenter: BasePresenter
}