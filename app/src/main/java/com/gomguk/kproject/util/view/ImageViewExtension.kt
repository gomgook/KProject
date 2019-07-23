package com.gomguk.kproject.util.view

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * ImageView에 image loading을 용이하게 하기 위한 extension.
 */
fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}