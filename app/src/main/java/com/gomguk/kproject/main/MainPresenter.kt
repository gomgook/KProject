package com.gomguk.kproject.main

import com.gomguk.kproject.util.model.DataWrapper

class MainPresenter(private val repository: MainRepository, val view: MainContract.View): MainContract.Presenter {
    fun loadData() {
        repository.getData(object : MainRepository.MainRepositoryListener {
            override fun onResult(data: DataWrapper) {
            }
        })
    }
}