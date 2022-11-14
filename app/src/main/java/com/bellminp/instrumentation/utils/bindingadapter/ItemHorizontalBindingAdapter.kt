package com.bellminp.instrumentation.utils.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R

@BindingAdapter("setTextSize")
fun setTextSize(tv: TextView, item: String) {
    tv.textSize = when (item) {
        "현장이름", "측정일시", "계측기명", "내용" -> InstrumentationApplication.mInstance.resources.getDimension(
            R.dimen.sp_16
        )
        else -> InstrumentationApplication.mInstance.resources.getDimension(R.dimen.sp_14)
    }
}

@BindingAdapter("hideTextSize")
fun hideTextSize(tv: TextView, item: String) {
    tv.textSize = InstrumentationApplication.mInstance.resources.getDimension(R.dimen.sp_14)
}

@BindingAdapter("setBackground")
fun setBackground(layout: ConstraintLayout, item: String) {
    layout.setBackgroundResource(
        when (item) {
            "현장이름", "측정일시", "계측기명", "내용" -> R.drawable.shape_table_title
            else -> R.drawable.shape_table
        }
    )
}