package com.gomguk.kproject.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gomguk.kproject.R

class MainActivity : AppCompatActivity(), MainContract.View {
    override lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(MainRepository.getInstance(), this)

        presenter.getData()
    }
}
