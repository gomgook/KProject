package com.gomguk.kproject.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.gomguk.kproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    override lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(MainRepository.getInstance(), this)

        swipeRefreshLayout.setAdapter(MainRecyclerViewAdapter())
        swipeRefreshLayout.setRecyclerView(recyclerView)
        swipeRefreshLayout.setLayoutManager(GridLayoutManager(this, 2))

        presenter.loadData()
    }
}
