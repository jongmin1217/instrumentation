package com.bellminp.instrumentation.ui.main.tree

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentTreeBinding
import com.bellminp.instrumentation.ui.base.BaseFragment
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldListAdapter
import com.bellminp.instrumentation.ui.dialog.fieldselect.FieldSelectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TreeFragment(
    private val fieldNum : Int
) : BaseFragment<FragmentTreeBinding,TreeViewModel>(R.layout.fragment_tree) {
    override val viewModel by activityViewModels<TreeViewModel>()

    val treeAdapter by lazy { TreeAdapter() }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        viewModel.getSites(fieldNum)
    }

    private fun initAdapter(){
        binding.recyclerviewTree.adapter = treeAdapter
    }

    override fun setupObserver() {
        super.setupObserver()

        with(viewModel){
            addField.observe(viewLifecycleOwner,{
                treeAdapter.submitList(listOf(it))
            })
        }
    }
}