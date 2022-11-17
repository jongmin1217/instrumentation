package com.bellminp.instrumentation.utils.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bellminp.instrumentation.mapper.mapToCellData
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.model.RecordListData
import com.bellminp.instrumentation.ui.publicadapter.VerticalAdapter

@BindingAdapter("setRecordItems")
fun setRecordItems(rv: RecyclerView, item: RecordListData) {
    val adapter = VerticalAdapter()
    rv.adapter = adapter
    val animator = rv.itemAnimator
    if (animator is SimpleItemAnimator) {
        animator.supportsChangeAnimations = false
    }
    adapter.submitList(item.list)
}