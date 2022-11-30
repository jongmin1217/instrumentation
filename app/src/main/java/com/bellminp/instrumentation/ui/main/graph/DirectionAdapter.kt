package com.bellminp.instrumentation.ui.main.graph

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bellminp.instrumentation.databinding.ItemFieldListBinding
import com.bellminp.instrumentation.databinding.ItemType5DirectionBinding
import com.bellminp.instrumentation.model.Direction
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder

class DirectionAdapter : BaseListAdapter<Direction>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Direction> {
        return Holder(
            ItemType5DirectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class Holder(
        private val binding : ItemType5DirectionBinding
    ) : BaseViewHolder<Direction>(binding){

        override fun bind(item: Direction) {
            binding.item = item
            binding.executePendingBindings()
        }

        override fun recycle() {}
    }
}