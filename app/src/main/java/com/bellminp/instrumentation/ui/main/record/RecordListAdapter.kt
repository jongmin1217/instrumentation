package com.bellminp.instrumentation.ui.main.record

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bellminp.instrumentation.databinding.ItemRecordListBinding
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.model.RecordListData
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder

class RecordListAdapter : BaseListAdapter<RecordListData>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RecordListData> {
        return RecordListHolder(
            ItemRecordListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    class RecordListHolder(
        val binding : ItemRecordListBinding
    ) : BaseViewHolder<RecordListData>(binding){

        override fun bind(item: RecordListData) {
            binding.item = item
            binding.executePendingBindings()

        }

        override fun recycle() {}
    }
}