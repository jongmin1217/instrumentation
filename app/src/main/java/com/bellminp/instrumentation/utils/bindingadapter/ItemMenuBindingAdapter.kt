package com.bellminp.instrumentation.utils.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("setImageResource")
fun bindImageResource(iv: ImageView, path: Int) {
    iv.setImageResource(path)
}