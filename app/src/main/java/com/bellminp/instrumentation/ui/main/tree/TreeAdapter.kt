package com.bellminp.instrumentation.ui.main.tree

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bellminp.instrumentation.databinding.*
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder

class TreeAdapter : BaseListAdapter<TreeModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TreeModel> {
        return when(viewType){
            FIELD -> {
                FieldHolder(
                    ItemFieldBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            SITES -> {
                SitesHolder(
                    ItemSitesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            SECTIONS -> {
                SectionsHolder(
                    ItemSectionsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            GAUGES -> {
                GaugesHolder(
                    ItemGaugesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                GaugesGroupHolder(
                    ItemGaugesGroupBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int) = when (currentList[position]) {
        is Field -> FIELD
        is SitesList -> SITES
        is SectionsList -> SECTIONS
        is GaugesList -> GAUGES
        is GaugesGroupList -> GAUGES_GROUP
        else -> throw IllegalStateException("can't find view type")
    }


    class FieldHolder(
        private val binding : ItemFieldBinding
    ) : BaseViewHolder<TreeModel>(binding){

        override fun bind(item: TreeModel) {
            if(item is Field){
                binding.item = item
                binding.executePendingBindings()
            }
        }

        override fun recycle() {
        }
    }

    class SitesHolder(
        private val binding : ItemSitesBinding
    ) : BaseViewHolder<TreeModel>(binding){

        override fun bind(item: TreeModel) {
            if(item is SitesList){
                binding.item = item
                binding.executePendingBindings()
            }
        }

        override fun recycle() {
        }
    }

    class SectionsHolder(
        private val binding : ItemSectionsBinding
    ) : BaseViewHolder<TreeModel>(binding){

        override fun bind(item: TreeModel) {
            if(item is SectionsList){
                binding.item = item
                binding.executePendingBindings()
            }
        }

        override fun recycle() {
        }
    }

    class GaugesHolder(
        private val binding : ItemGaugesBinding
    ) : BaseViewHolder<TreeModel>(binding){

        override fun bind(item: TreeModel) {
            if(item is GaugesList){
                binding.item = item
                binding.executePendingBindings()
            }
        }

        override fun recycle() {
        }
    }

    class GaugesGroupHolder(
        private val binding : ItemGaugesGroupBinding
    ) : BaseViewHolder<TreeModel>(binding){

        override fun bind(item: TreeModel) {
            if(item is GaugesGroupList){
                binding.item = item
                binding.executePendingBindings()
            }
        }

        override fun recycle() {
        }
    }

    companion object {
        const val FIELD = 0
        const val SITES = 1
        const val SECTIONS = 2
        const val GAUGES = 3
        const val GAUGES_GROUP = 4
    }
}