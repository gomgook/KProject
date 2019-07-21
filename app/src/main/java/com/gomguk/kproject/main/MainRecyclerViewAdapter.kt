package com.gomguk.kproject.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gomguk.kproject.R

class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parentView: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(parentView)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, p1: Int) {
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
    }
}