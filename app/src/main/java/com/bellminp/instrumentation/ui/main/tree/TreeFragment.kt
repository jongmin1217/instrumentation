package com.bellminp.instrumentation.ui.main.tree

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentTreeBinding
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.ui.base.BaseFragment
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldListAdapter
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldSelectViewModel
import com.bellminp.instrumentation.ui.main.MainViewModel
import com.bellminp.instrumentation.utils.ONE_DAY
import com.bellminp.instrumentation.utils.convertTimestampToDateTerm
import com.bellminp.instrumentation.utils.convertTimestampToDateText
import com.bellminp.instrumentation.utils.getUnixTime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TreeFragment(
    var fieldNum: Int,
    private val gaugesClick: ((item: SelectData) -> Unit)
) : BaseFragment<FragmentTreeBinding, TreeViewModel>(R.layout.fragment_tree) {
    override val viewModel by activityViewModels<TreeViewModel>()
    private val activityViewModel by activityViewModels<MainViewModel>()

    private val treeAdapter by lazy {
        TreeAdapter(
            {
                when (it) {
                    is Field -> viewModel.getSites(fieldNum)
                    is SitesList -> viewModel.getSections(it.num)
                    is SectionsList -> viewModel.getGauges(it.num)
                    is GaugesList -> viewModel.getGaugesGroup(it.num)
                }
            },
            {
                clickGauges(it)
            },
            {
                unSelectGauges()
            }
        )
    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        viewModel.initSearch(fieldNum)
    }

    fun refresh(){
        treeAdapter.selectNum = null
        treeAdapter.submitList(null)
        viewModel.initSearch(fieldNum)
    }



    private fun initAdapter() {
        binding.recyclerviewTree.adapter = treeAdapter
        val animator = binding.recyclerviewTree.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    override fun setupObserver() {
        super.setupObserver()

        with(viewModel) {
            addField.observe(viewLifecycleOwner) {
                activityViewModel.setHeader(it.name)
            }

            addSites.observe(viewLifecycleOwner) {
                treeAdapter.addSites(it)
            }

            addSections.observe(viewLifecycleOwner) {
                treeAdapter.addSections(it)
            }

            addGauges.observe(viewLifecycleOwner) {
                treeAdapter.addGauges(it)
            }

            addGaugesGroup.observe(viewLifecycleOwner) {
                treeAdapter.addGaugesGroup(it)
            }

            addSectionsNone.observe(viewLifecycleOwner) {
                treeAdapter.addSectionsNone(it)
            }

            addGaugesNone.observe(viewLifecycleOwner) {
                treeAdapter.addGaugesNone(it)
            }

            addGaugesGroupNone.observe(viewLifecycleOwner) {
                treeAdapter.addGaugesGroupNone(it)
            }
        }
    }

    private fun clickGauges(item: TreeModel) {
        val time = if(item.getGaugesTime() == null) getUnixTime()
        else item.getGaugesTime()!!

        gaugesClick(
            SelectData(
                convertTimestampToDateText(
                    getUnixTime(
                        convertTimestampToDateTerm(time),
                        false
                    )
                ),
                convertTimestampToDateText(
                    getUnixTime(
                        convertTimestampToDateTerm(time - (ONE_DAY * 3)),
                        true
                    )
                ),
                "3",
                if (item is GaugesList) treeAdapter.getSectionsName(item.getSectionsNum())
                else treeAdapter.getGroupName(item.getGroupNum()),
                item.getGaugesName(),
                getUnixTime(
                    convertTimestampToDateTerm(time - (ONE_DAY * 3)),
                    true
                ) / 1000,
                getUnixTime(convertTimestampToDateTerm(time), false) / 1000,
                item.getGaugesNum(),
                item.getGaugesType()
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshTree(){
        treeAdapter.notifyDataSetChanged()
    }

    private fun unSelectGauges() {
        gaugesClick(
            SelectData(
                selectSections = "",
                selectGauges = "",
                gaugesNum = 0,
                type = ""
            )
        )
    }

    fun toRecord(num : Int){
        treeAdapter.toRecord(num)
    }
}