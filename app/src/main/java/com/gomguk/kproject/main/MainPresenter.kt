package com.gomguk.kproject.main

import com.gomguk.kproject.util.model.DataWrapper

class MainPresenter(private val repository: MainContract.Model, val view: MainContract.View): MainContract.Presenter {
    private var isLoading = false

    override fun isLoading(): Boolean {
        return isLoading
    }

    override fun loadData(isAdd: Boolean) {
        isLoading = true

        repository.getData(isAdd, object : MainContract.Model.MainRepositoryListener {
            override fun onResult(data: DataWrapper, isAdd: Boolean) {
                view.setAdapterData(data.documents, isAdd)

                isLoading = false
            }
        })
    }
}