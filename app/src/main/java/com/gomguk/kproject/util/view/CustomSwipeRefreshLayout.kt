package com.gomguk.kproject.util.view

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.gomguk.kproject.main.MainRecyclerViewAdapter

class CustomSwipeRefreshLayout: SwipeRefreshLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainRecyclerViewAdapter

    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    fun setAdapter(adapter: MainRecyclerViewAdapter) {
        this.adapter = adapter
    }

    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        recyclerView.layoutManager = layoutManager
    }
}