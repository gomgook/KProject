package com.gomguk.kproject.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.gomguk.kproject.R
import com.gomguk.kproject.content.ContentActivity
import com.gomguk.kproject.util.model.Document
import com.gomguk.kproject.util.view.loadImage

class MainRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {
    private var data: List<Document> = ArrayList()

    fun setData(data: List<Document>, isAdd: Boolean) {
        if (isAdd) {
            this.data = this.data.plus(data)
        } else {
            this.data = data
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_main_recycler_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentData = data[position]

        viewHolder.layout.setOnClickListener {
            val intent = Intent(context, ContentActivity::class.java)

            intent.putExtra("data", currentData)
            context.startActivity(intent)
        }
        currentData.thumbnail?.let {
            viewHolder.image.loadImage(it)
        }
        viewHolder.title.text = currentData.title
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: RelativeLayout = itemView.findViewById(R.id.layout)
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
    }
}