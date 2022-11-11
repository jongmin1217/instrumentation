package com.bellminp.instrumentation.utils.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bellminp.instrumentation.model.Field

@BindingAdapter("setFieldName")
fun setFieldName(tv: TextView, item: Field) {
    tv.text = item.name
}