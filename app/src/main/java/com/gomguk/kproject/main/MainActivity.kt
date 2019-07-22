package com.gomguk.kproject.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.gomguk.kproject.R
import com.gomguk.kproject.util.model.Document
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    override lateinit var presenter: MainPresenter

    private val adapter: MainRecyclerViewAdapter = MainRecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(MainRepository.getInstance(), this)

        swipeRefreshLayout.setRecyclerView(recyclerView)
        swipeRefreshLayout.setAdapter(adapter)
        swipeRefreshLayout.setLayoutManager(GridLayoutManager(this, 2))
        swipeRefreshLayout.setOnRefreshListener {
            presenter.loadData()
        }

        // First loading.
        presenter.loadData()
    }

    override fun setAdapterData(data: List<Document>) {
        adapter.setData(data)

        // Flag to end refresh.
        swipeRefreshLayout.isRefreshing = false
    }
}
