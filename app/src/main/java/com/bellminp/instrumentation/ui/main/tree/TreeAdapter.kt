package com.bellminp.instrumentation.ui.main.tree

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.databinding.*
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder
import com.bellminp.instrumentation.utils.removeSlice
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class TreeAdapter(
    private val onItemClick: ((item: TreeModel) -> Unit),
    private val selectGauges: ((item: TreeModel) -> Unit),
    private val unSelectGauges: (() -> Unit)
) : BaseListAdapter<TreeModel>() {

    var selectNum: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TreeModel> {
        return when (viewType) {
            FIELD -> {
                FieldHolder(
                    ItemFieldBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ).also {

                    it.itemView.setOnClickListener { _ ->
                        val item = currentList[it.adapterPosition]
                        if (item is Field) {
                            item.checked = !item.checked
                            notifyItemChanged(it.adapterPosition)

                            if (item.checked) onItemClick(currentList[it.adapterPosition])
                            else closeField()
                        }
                    }
                }
            }

            SITES -> {
                SitesHolder(
                    ItemSitesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ).also {

                    it.itemView.setOnClickListener { _ ->
                        val item = currentList[it.adapterPosition]
                        if (item is SitesList) {
                            item.checked = !item.checked
                            notifyItemChanged(it.adapterPosition)

                            if (item.checked) onItemClick(currentList[it.adapterPosition])
                            else closeSites(it.adapterPosition)
                        }
                    }


                }
            }

            SECTIONS -> {
                SectionsHolder(
                    ItemSectionsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ).also {

                    it.itemView.setOnClickListener { _ ->
                        val item = currentList[it.adapterPosition]
                        if (item is SectionsList) {
                            item.checked = !item.checked
                            notifyItemChanged(it.adapterPosition)

                            if (item.checked) onItemClick(currentList[it.adapterPosition])
                            else closeSections(it.adapterPosition)
                        }
                    }


                }
            }

            GAUGES -> {
                GaugesHolder(
                    ItemGaugesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ).also {

                    it.itemView.setOnClickListener { _ ->
                        val item = currentList[it.adapterPosition]
                        if (item is GaugesList) {
                            item.clicked = !item.clicked
                            notifyItemChanged(it.adapterPosition)
                            gaugesClick(item.num)
                            if (item.clicked) {
                                selectNum = item.num
                                selectGauges(currentList[it.adapterPosition])
                            } else unSelectGauges()
                        }
                    }

                    it.binding.ivMore.setOnClickListener { _ ->
                        val item = currentList[it.adapterPosition]
                        if (item is GaugesList) {
                            item.checked = !item.checked
                            notifyItemChanged(it.adapterPosition)

                            if (item.checked) onItemClick(currentList[it.adapterPosition])
                            else closeGauges(it.adapterPosition)
                        }
                    }

                }
            }

            else -> {
                GaugesGroupHolder(
                    ItemGaugesGroupBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ).also {
                    it.itemView.setOnClickListener { _ ->
                        val item = currentList[it.adapterPosition]
                        if (item is GaugesGroupList) {
                            item.clicked = !item.clicked
                            notifyItemChanged(it.adapterPosition)
                            gaugesClick(item.num)
                            if (item.clicked) {
                                selectNum = item.num
                                selectGauges(currentList[it.adapterPosition])
                            } else unSelectGauges()
                        }
                    }
                }
            }
        }
    }

    fun toRecord(num : Int){
        val index = currentList.indexOfFirst { it is GaugesList && it.num == num}
        if(index != -1){
            val item = currentList[index]
            if(item is GaugesList){
                item.clicked = true
                notifyItemChanged(index)
                gaugesClick(num)

            }
        }else{
            val clickedSize = (currentList.filter { (it is GaugesList && it.clicked) || (it is GaugesGroupList && it.clicked)}).size
            for(i in 0 until clickedSize){
                gaugesClick(num)
            }
        }
        selectNum = num
    }

    private fun gaugesClick(num: Int) {
        val index =
            currentList.indexOfFirst { (it is GaugesList && it.clicked && it.num != num) || (it is GaugesGroupList && it.clicked && it.num != num) }
        if (index != -1) {
            if (currentList[index] is GaugesList) (currentList[index] as GaugesList).clicked = false
            if (currentList[index] is GaugesGroupList) (currentList[index] as GaugesGroupList).clicked =
                false
            notifyItemChanged(index)
            submitList(currentList)
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

    private fun closeField() {
        val tempList = listOf(currentList[0])
        submitList(tempList)
    }

    private fun closeSites(position: Int) {
        val item = currentList[position]
        if (item is SitesList) {
            val sitesNum = item.num
            val tempList = ArrayList(currentList)
            tempList.removeAll { it is SectionsList && it.siteNum == sitesNum }
            tempList.removeAll { it is GaugesList && it.siteNum == sitesNum }
            tempList.removeAll { it is GaugesGroupList && it.siteNum == sitesNum }
            submitList(tempList)
        }
    }

    private fun closeSections(position: Int) {
        val item = currentList[position]
        if (item is SectionsList) {
            val sectionsNum = item.num
            val tempList = ArrayList(currentList)
            tempList.removeAll { it is GaugesList && it.sectionNum == sectionsNum }
            tempList.removeAll { it is GaugesGroupList && it.sectionNum == sectionsNum }
            submitList(tempList)
        }
    }

    private fun closeGauges(position: Int) {
        val item = currentList[position]
        if (item is GaugesList) {
            val gaugesNum = item.num
            val tempList = ArrayList(currentList)
            tempList.removeAll { it is GaugesGroupList && it.gaugegroupNum == gaugesNum }
            submitList(tempList)
        }
    }

    fun addSites(items: List<SitesList>) {
        val index = currentList.indexOfFirst { it is Field }
        if (index != -1) addAll(
            index + 1,
            items.apply { items[items.size - 1].bottomViewVisible = false })
    }

    fun addSections(items: List<SectionsList>) {
        val index = currentList.indexOfFirst { it is SitesList && it.num == items[0].siteNum }
        if (index != -1) {

            addAll(index + 1, items.apply {
                this[this.size - 1].bottomViewVisible = false
                this.forEach {
                    it.sitesLineVisible = (currentList[index] as SitesList).bottomViewVisible
                }
            })
        }
    }

    fun addGauges(items: List<GaugesList>) {
        val index = currentList.indexOfFirst { it is SectionsList && it.num == items[0].sectionNum }
        if (index != -1) {

            addAll(index + 1, items.apply {
                selectNum?.let { num ->
                    val selectIndex = this.indexOfFirst { it.num == num }
                    if (selectIndex != -1) this[selectIndex].clicked = true
                }
                this[this.size - 1].bottomViewVisible = false
                this.forEach {
                    it.sitesLineVisible = (currentList[index] as SectionsList).sitesLineVisible
                }
                this.forEach {
                    it.sectionsLineVisible = (currentList[index] as SectionsList).bottomViewVisible
                }
                this.forEach {
                    it.siteNum = (currentList[index] as SectionsList).siteNum
                }
            })
        }
    }

    fun addGaugesGroup(items: List<GaugesGroupList>) {
        val index =
            currentList.indexOfFirst { it is GaugesList && it.type == "group" && it.num == items[0].gaugegroupNum }
        if (index != -1) {

            addAll(index + 1, items.apply {
                selectNum?.let { num ->
                    val selectIndex = this.indexOfFirst { it.num == num }
                    if (selectIndex != -1) this[selectIndex].clicked = true
                }
                this[this.size - 1].bottomViewVisible = false
                this.forEach {
                    it.sitesLineVisible = (currentList[index] as GaugesList).sitesLineVisible
                }
                this.forEach {
                    it.sectionsLineVisible = (currentList[index] as GaugesList).sectionsLineVisible
                }
                this.forEach {
                    it.gaugesLineVisible = (currentList[index] as GaugesList).bottomViewVisible
                }
                this.forEach {
                    it.siteNum = (currentList[index] as GaugesList).siteNum
                }
            })
        }
    }

    fun addSectionsNone(num: Int) {
        val index = currentList.indexOfFirst { it is SitesList && it.num == num }
        if (index != -1) {
            currentList[index].noneItem()
            notifyItemChanged(index)
        }
    }

    fun addGaugesNone(num: Int) {
        val index = currentList.indexOfFirst { it is SectionsList && it.num == num }
        if (index != -1) {
            currentList[index].noneItem()
            notifyItemChanged(index)
        }
    }

    fun addGaugesGroupNone(num: Int) {
        val index =
            currentList.indexOfFirst { it is GaugesList && it.type == "group" && it.num == num }
        if (index != -1) {
            currentList[index].noneItem()
            notifyItemChanged(index)
        }
    }

    fun getSectionsName(num: Int): String {
        val index = currentList.indexOfFirst { it is SectionsList && it.num == num }
        return if (index != -1) {
            val item = currentList[index] as SectionsList
            val managenumText = if (item.managenum.isEmpty()) "" else "[${item.managenum}]"
            "${item.name} $managenumText"
        } else ""
    }

    fun getGroupName(num: Int): String {
        val index = currentList.indexOfFirst { it is GaugesList && it.num == num }
        return if (index != -1) {
            val item = currentList[index] as GaugesList
            val managenumText = if (item.managenum.isEmpty()) "" else "[${item.managenum}]"
            "${item.name} $managenumText"
        } else ""
    }


    class FieldHolder(
        private val binding: ItemFieldBinding
    ) : BaseViewHolder<TreeModel>(binding) {

        override fun bind(item: TreeModel) {
            if (item is Field) {
                binding.item = item
                binding.executePendingBindings()
            }
        }

        override fun recycle() {
        }
    }

    class SitesHolder(
        val binding: ItemSitesBinding
    ) : BaseViewHolder<TreeModel>(binding) {

        override fun bind(item: TreeModel) {
            if (item is SitesList) {
                binding.item = item
                binding.executePendingBindings()

            }
        }

        override fun recycle() {
        }


    }

    class SectionsHolder(
        val binding: ItemSectionsBinding
    ) : BaseViewHolder<TreeModel>(binding) {

        override fun bind(item: TreeModel) {
            if (item is SectionsList) {
                binding.item = item
                binding.executePendingBindings()
            }
        }

        override fun recycle() {
        }
    }

    class GaugesHolder(
        val binding: ItemGaugesBinding
    ) : BaseViewHolder<TreeModel>(binding) {

        override fun bind(item: TreeModel) {
            if (item is GaugesList) {
                binding.item = item
                binding.executePendingBindings()
            }
        }

        override fun recycle() {
        }
    }

    class GaugesGroupHolder(
        private val binding: ItemGaugesGroupBinding
    ) : BaseViewHolder<TreeModel>(binding) {

        override fun bind(item: TreeModel) {
            if (item is GaugesGroupList) {
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