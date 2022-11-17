package com.bellminp.instrumentation.ui.main.table

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.databinding.ItemRecordListBinding
import com.bellminp.instrumentation.databinding.ItemTableBinding
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.model.TableData
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder

class TableAdapter : BaseListAdapter<TableData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TableData> {
        return TableHolder(
            ItemTableBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class TableHolder(
        val binding : ItemTableBinding
    ) : BaseViewHolder<TableData>(binding){

        override fun bind(item: TableData) {
            binding.item = item
            binding.executePendingBindings()

        }

        override fun recycle() {}
    }
}