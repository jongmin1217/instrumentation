package com.bellminp.instrumentation.utils.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.mapper.mapToCellData
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.ui.publicadapter.HorizontalAdapter

@BindingAdapter("setRecordItems")
fun setRecordItems(rv: RecyclerView, item: RecordData) {
    val adapter = HorizontalAdapter()
    rv.adapter = adapter
    val animator = rv.itemAnimator
    if (animator is SimpleItemAnimator) {
        animator.supportsChangeAnimations = false
    }
    adapter.submitList(item.mapToCellData(item.maxData))
}