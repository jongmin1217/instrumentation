package com.bellminp.instrumentation.utils.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bellminp.instrumentation.model.TableData
import com.bellminp.instrumentation.ui.publicadapter.VerticalAdapter

@BindingAdapter("setTableItems")
fun setTableItems(rv: RecyclerView, item: TableData) {
    val adapter = VerticalAdapter()
    rv.adapter = adapter
    val animator = rv.itemAnimator
    if (animator is SimpleItemAnimator) {
        animator.supportsChangeAnimations = false
    }
    adapter.submitList(item.list)
}