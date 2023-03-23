package com.bellminp.instrumentation.ui.usinginfo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bellminp.instrumentation.databinding.ItemFieldListBinding
import com.bellminp.instrumentation.databinding.ItemUsingInfoBinding
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.model.UsingInfoData
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder

class UsingInfoAdapter : BaseListAdapter<UsingInfoData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<UsingInfoData> {
        return FieldListHolder(
            ItemUsingInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class FieldListHolder(
        private val binding: ItemUsingInfoBinding
    ) : BaseViewHolder<UsingInfoData>(binding) {

        override fun bind(item: UsingInfoData) {
            binding.item = item
            binding.executePendingBindings()
        }

        override fun recycle() {}
    }
}