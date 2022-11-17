package com.bellminp.instrumentation.ui.main.tree

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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TreeFragment(
    private val fieldNum: Int,
    private val gaugesClick: ((item: SelectData) -> Unit)
) : BaseFragment<FragmentTreeBinding, TreeViewModel>(R.layout.fragment_tree) {
    override val viewModel by activityViewModels<TreeViewModel>()

    private val treeAdapter by lazy {
        TreeAdapter(
            {
                when (it) {
                    is Field -> viewModel.getSites(fieldNum, false)
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
        viewModel.getSites(fieldNum, true)
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
                treeAdapter.submitList(listOf(it))
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
        }
    }

    private fun clickGauges(item: TreeModel) {
        gaugesClick(
            SelectData(
                selectSections = if (item is GaugesList) treeAdapter.getSectionsName(item.getSectionsNum())
                else treeAdapter.getGroupName(item.getGroupNum()),
                selectGauges = item.getGaugesName(),
                gaugesNum = item.getGaugesNum(),
                type = item.getGaugesType()
            )
        )
    }

    private fun unSelectGauges(){
        gaugesClick(
            SelectData(
                selectSections = "",
                selectGauges = "",
                gaugesNum = 0,
                type = ""
            )
        )
    }
}