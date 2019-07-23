package com.gomguk.kproject.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.gomguk.kproject.R
import com.gomguk.kproject.util.Constants.Companion.PAGE_SIZE
import com.gomguk.kproject.util.model.Document
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter: MainContract.Presenter by inject { parametersOf(this) }
    private val adapter: MainRecyclerViewAdapter = MainRecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout.setRecyclerView(recyclerView)
        swipeRefreshLayout.setAdapter(adapter)
        swipeRefreshLayout.setLayoutManager(GridLayoutManager(this, 2))
        swipeRefreshLayout.setOnRefreshListener {
            presenter.loadData(false)
        }
        swipeRefreshLayout.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = swipeRefreshLayout.recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!presenter.isLoading()) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE
                    ) {
                        presenter.loadData(true)
                    }
                }
            }
        })

        // First loading.
        presenter.loadData(false)
    }

    override fun setAdapterData(data: List<Document>, isAdd: Boolean) {
        adapter.setData(data, isAdd)

        // Flag to end refresh.
        swipeRefreshLayout.isRefreshing = false
    }
}
