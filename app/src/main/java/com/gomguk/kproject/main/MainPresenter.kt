package com.gomguk.kproject.main

class MainPresenter(val repository: MainRepository, val view: MainContract.View): MainContract.Presenter {
    fun getData() {
        repository.getData()
    }
}