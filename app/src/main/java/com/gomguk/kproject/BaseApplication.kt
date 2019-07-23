package com.gomguk.kproject

import android.app.Application
import com.gomguk.kproject.util.di.modules
import org.koin.android.ext.android.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Koin을 사용해 DI를 사용하기 위해서 Application에서 지정한 module들을 start 시켜주어야 한다.
        startKoin(this, modules)
    }
}