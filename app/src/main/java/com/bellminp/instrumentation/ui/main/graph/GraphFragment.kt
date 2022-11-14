package com.bellminp.instrumentation.ui.main.graph

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentGraphBinding
import com.bellminp.instrumentation.model.SelectData
import com.bellminp.instrumentation.ui.base.BaseFragment
import com.bellminp.instrumentation.ui.main.tree.TreeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GraphFragment(
    private val selectData : SelectData,
    private val selectedData : ((date : SelectData) -> Unit)
) : BaseFragment<FragmentGraphBinding,GraphViewModel>(R.layout.fragment_graph) {
    override val viewModel by activityViewModels<GraphViewModel>()

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        initListener()
    }

    fun setSelectData(selectData : SelectData){
        with(viewModel){
            fromDay.value = selectData.fromDay
            days.value = selectData.days
            toDay.value = selectData.toDay
            selectSections.value = selectData.selectSections
            selectGauges.value = selectData.selectGauges
        }
    }

    private fun initLayout(){
        setSelectData(selectData)
    }

    private fun initListener(){
        binding.layoutFromValue.setOnClickListener {
            showDateSelect()
        }

        binding.layoutDaysValue.setOnClickListener {
            showDateSelect()
        }

        binding.layoutToValue.setOnClickListener {
            showDateSelect()
        }
    }

    override fun selectedDate(selectData: SelectData) {
        selectedData(selectData)
    }
}