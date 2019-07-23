package com.gomguk.kproject.util.view

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi

/**
 * 기기 api에 따른 ripple animation 대응.
 */
fun View.applyRippleAnimation() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        applyRippleCompatMarshmallow(this)
    } else {
        applyRippleCompatPreMarshmallow(this)
    }
}

private fun applyRippleCompatPreMarshmallow(view: View) {
    val attrs = intArrayOf(android.R.attr.selectableItemBackground)
    val ta = view.context.obtainStyledAttributes(attrs)
    val drawable = ta.getDrawable(0)
    ta.recycle()
    view.background = drawable
}

@RequiresApi(api = Build.VERSION_CODES.M)
private fun applyRippleCompatMarshmallow(view: View) {
    val attrs = intArrayOf(android.R.attr.selectableItemBackgroundBorderless)
    val ta = view.context.obtainStyledAttributes(attrs)
    val drawable = ta.getDrawable(0)
    ta.recycle()
    view.foreground = drawable
}