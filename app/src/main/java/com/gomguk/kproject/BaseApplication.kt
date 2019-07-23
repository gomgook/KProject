package com.gomguk.kproject

import android.app.Application
import com.gomguk.kproject.util.di.applicationModule
import org.koin.android.ext.android.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(applicationModule))
    }
}