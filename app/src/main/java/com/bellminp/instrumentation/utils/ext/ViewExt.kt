package com.bellminp.instrumentation.utils.ext

import android.view.View
import android.view.ViewGroup

fun View.margin(dp: Float) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        topMargin = dp.toInt()
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}