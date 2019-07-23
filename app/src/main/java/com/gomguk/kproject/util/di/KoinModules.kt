package com.gomguk.kproject.util.di

import com.gomguk.kproject.content.ContentContract
import com.gomguk.kproject.content.ContentPresenter
import com.gomguk.kproject.content.ContentRepository
import com.gomguk.kproject.main.MainContract
import com.gomguk.kproject.main.MainPresenter
import com.gomguk.kproject.main.MainRepository
import com.google.gson.Gson
import org.koin.dsl.module.module

val applicationModule = module {
    factory<MainContract.Presenter> { (view: MainContract.View) ->
        MainPresenter(
            MainRepository.getInstance(),
            view
        )
    }
    factory<ContentContract.Presenter> { (view: ContentContract.View) ->
        ContentPresenter(
            ContentRepository.getInstance(),
            view
        )
    }
}

val gSonModule = module {
    single { Gson() }
}

val modules = listOf(applicationModule, gSonModule)