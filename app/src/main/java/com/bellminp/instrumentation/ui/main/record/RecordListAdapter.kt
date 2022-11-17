package com.bellminp.instrumentation.ui.main.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.databinding.ItemFieldListBinding
import com.bellminp.instrumentation.databinding.ItemRecordListBinding
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder
import com.bellminp.instrumentation.ui.publicadapter.HorizontalAdapter

class RecordListAdapter : BaseListAdapter<RecordData>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RecordData> {
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
    ) : BaseViewHolder<RecordData>(binding){

        override fun bind(item: RecordData) {
            binding.item = item
            binding.executePendingBindings()

        }

        override fun recycle() {}
    }
}