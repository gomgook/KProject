package com.gomguk.kproject.util.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gomguk.kproject.main.MainRecyclerViewAdapter

class CustomSwipeRefreshLayout: SwipeRefreshLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private lateinit var recyclerView: RecyclerView

    fun getRecyclerView(): RecyclerView {
        return recyclerView
    }

    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    fun setAdapter(adapter: MainRecyclerViewAdapter) {
        recyclerView.adapter = adapter
    }

    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        recyclerView.layoutManager = layoutManager
    }
}