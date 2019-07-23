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
    // Main 화면에 사용되는 DI
    factory<MainContract.Presenter> { (view: MainContract.View) ->
        MainPresenter(
            MainRepository.getInstance(),
            view
        )
    }

    // Content 화면에 사용되는 DI.
    factory<ContentContract.Presenter> { (view: ContentContract.View) ->
        ContentPresenter(
            ContentRepository.getInstance(),
            view
        )
    }
}

val gSonModule = module {

    // Gson 객체 DI.
    // TODO - 메모리 사용 개선을 위한 Gson 객체 singleton화.
    single { Gson() }
}

val modules = listOf(applicationModule, gSonModule)