package com.bellminp.instrumentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.databinding.ItemFieldListBinding
import com.bellminp.instrumentation.databinding.ItemMenuBinding
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.model.MenuData
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder

class MenuAdapter(
    private val onItemClick : ((item : MenuData) -> Unit)
) : BaseListAdapter<MenuData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MenuData> {
        return MenuHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).also {
            it.itemView.setOnClickListener{_->
                val currentItem = currentList.getOrNull(it.adapterPosition)
                currentItem?.let{ item->
                    if(!item.checked){
                        select(item.id,it.adapterPosition)
                        onItemClick(item)
                    }
                }
            }
        }
    }

    private fun select(id : Int, position : Int){
        currentList[position].checked = true
        notifyItemChanged(position)

        val index = currentList.indexOfFirst { it.checked && it.id != id }
        if(index != -1){
            currentList[index].checked = false
            notifyItemChanged(index)
        }

        submitList(currentList)
    }

    class MenuHolder(
        private val binding : ItemMenuBinding
    ) : BaseViewHolder<MenuData>(binding){

        override fun bind(item: MenuData) {
            binding.item = item
            binding.executePendingBindings()

        }

        override fun recycle() {
        }
    }
}