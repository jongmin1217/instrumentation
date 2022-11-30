package com.bellminp.instrumentation.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bellminp.instrumentation.ui.main.tree.TreeAdapter


abstract class BaseListAdapter<T> :
    ListAdapter<T, BaseViewHolder<T>>(object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(currentList[position])
    }

    fun removeAt(position: Int){
        val tempList = currentList
        if(position < currentList.size){
            tempList.removeAt(position)
        }
        submitList(tempList)
    }

    fun addAll(items: List<T>) {
        val tempList = currentList.toMutableList()
        tempList.addAll(items)
        submitList(tempList)
    }

    fun addAll(position: Int, items: List<T>) {
        val tempList = currentList.toMutableList()
        tempList.addAll(position, items)
        submitList(tempList)
    }


}