package com.bellminp.instrumentation.ui.dialog.fieldselect

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bellminp.common.timberMsg
import com.bellminp.domain.model.DomainFieldList
import com.bellminp.instrumentation.databinding.ItemFieldListBinding
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder

class FieldListAdapter(
    private val onItemClick : ((item : FieldList) -> Unit)
) : BaseListAdapter<FieldList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FieldList> {
        return FieldListHolder(
            ItemFieldListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).also {
            it.itemView.setOnClickListener{_->
                val currentItem = currentList.getOrNull(it.adapterPosition)
                currentItem?.let(onItemClick)
            }
        }
    }

    class FieldListHolder(
        private val binding : ItemFieldListBinding
    ) : BaseViewHolder<FieldList>(binding){

        override fun bind(item: FieldList) {
            binding.item = item
            binding.executePendingBindings()
        }

        override fun recycle() {}
    }
}