package com.bellminp.instrumentation.ui.main.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bellminp.instrumentation.databinding.ItemRecordListBinding
import com.bellminp.instrumentation.model.CellData
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.model.RecordListData
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder
import com.bellminp.instrumentation.ui.publicadapter.VerticalAdapter

class RecordListAdapter(
    private val onItemClick : ((item : CellData) -> Unit)
) : BaseListAdapter<RecordListData>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RecordListData> {
        return RecordListHolder(
            ItemRecordListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ){
            onItemClick(it)
        }
    }



    class RecordListHolder(
        val binding : ItemRecordListBinding,
        private val onItemClick : ((item : CellData) -> Unit)
    ) : BaseViewHolder<RecordListData>(binding){

        override fun bind(item: RecordListData) {
            binding.item = item
            binding.executePendingBindings()

            val adapter = VerticalAdapter{
                onItemClick(it)
            }
            binding.recyclerviewVertical.adapter = adapter
            val animator =  binding.recyclerviewVertical.itemAnimator
            if (animator is SimpleItemAnimator) {
                animator.supportsChangeAnimations = false
            }
            adapter.submitList(item.list)

        }

        override fun recycle() {}
    }
}