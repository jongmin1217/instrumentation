package com.bellminp.instrumentation.ui.main.graph

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bellminp.instrumentation.databinding.ItemFieldListBinding
import com.bellminp.instrumentation.databinding.ItemLegendBinding
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.model.LegendData
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder

class LegendAdapter: BaseListAdapter<LegendData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<LegendData> {
        return FieldListHolder(
            ItemLegendBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class FieldListHolder(
        private val binding : ItemLegendBinding
    ) : BaseViewHolder<LegendData>(binding){

        override fun bind(item: LegendData) {
            binding.item = item
            binding.executePendingBindings()
        }

        override fun recycle() {}
    }
}