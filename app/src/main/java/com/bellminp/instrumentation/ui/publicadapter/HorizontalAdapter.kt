package com.bellminp.instrumentation.ui.publicadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bellminp.instrumentation.databinding.ItemHorizontalTableBinding
import com.bellminp.instrumentation.databinding.ItemRecordListBinding
import com.bellminp.instrumentation.model.CellData
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder

class HorizontalAdapter : BaseListAdapter<CellData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CellData> {
        return HorizontalHolder(
            ItemHorizontalTableBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class HorizontalHolder(
        private val binding : ItemHorizontalTableBinding
    ) : BaseViewHolder<CellData>(binding){

        override fun bind(item: CellData) {
            binding.item = item
            binding.executePendingBindings()
        }

        override fun recycle() {}
    }
}