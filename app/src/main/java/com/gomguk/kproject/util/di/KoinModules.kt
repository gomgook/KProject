package com.gomguk.kproject.util.di

import com.gomguk.kproject.content.ContentContract
import com.gomguk.kproject.content.ContentPresenter
import com.gomguk.kproject.content.ContentRepository
import com.gomguk.kproject.main.MainContract
import com.gomguk.kproject.main.MainPresenter
import com.gomguk.kproject.main.MainRepository
import org.koin.dsl.module.module

val applicationModule = module(override = true) {
    factory<MainContract.Presenter> { (repository: MainRepository, view: MainContract.View) ->
        MainPresenter(
            repository,
            view
        )
    }
    factory<ContentContract.Presenter> { (repository: ContentRepository, view: ContentContract.View) ->
        ContentPresenter(
            repository,
            view
        )
    }
    single { MainRepository }
}