package com.gomguk.kproject.main

class MainPresenter(private val repository: MainRepository, val view: MainContract.View): MainContract.Presenter {
    fun loadData() {
        repository.getData()
    }
}