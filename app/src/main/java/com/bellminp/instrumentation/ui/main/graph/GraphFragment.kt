package com.bellminp.instrumentation.ui.main.graph

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentGraphBinding
import com.bellminp.instrumentation.mapper.dataToGraph
import com.bellminp.instrumentation.mapper.dataToGraph3
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.ui.base.BaseFragment
import com.bellminp.instrumentation.ui.main.tree.TreeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GraphFragment(
    private val selectData : SelectData,
    private val gaugesData: GaugesData?,
    private val selectedData : ((date : SelectData) -> Unit)
) : BaseFragment<FragmentGraphBinding,GraphViewModel>(R.layout.fragment_graph) {
    override val viewModel by activityViewModels<GraphViewModel>()

    val adapter = GraphAdapter()

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        initListener()
        initAdapter()
        settingGraphData(gaugesData)
    }

    fun settingGraphData(data : GaugesData?){
        viewModel.gaugesData.value = data

        when(data){
            is GaugesDetail -> adapter.submitList(data.dataToGraph())
            is GaugesGroupDetail -> {
                when(data.chartType){
                    2 -> adapter.submitList(data.dataToGraph())
                    3 -> {
                        timberMsg(data.dataToGraph3())
                        adapter.submitList(data.dataToGraph3())
                    }
                }
            }
        }

    }

    fun setSelectData(selectData : SelectData){
        with(viewModel){
            fromDay.value = selectData.fromDay
            days.value = selectData.days
            toDay.value = selectData.toDay
            selectSections.value = selectData.selectSections
            selectGauges.value = selectData.selectGauges

            startUnixTime = selectData.startUnixTime
            endUnixTime = selectData.endUnixTime
        }
    }

    private fun initAdapter(){
        binding.recyclerviewGraph.adapter = adapter
    }

    private fun initLayout(){
        setSelectData(selectData)
    }

    private fun initListener(){
        binding.layoutFromValue.setOnClickListener {
            showDateSelect(viewModel.startUnixTime,viewModel.endUnixTime)
        }

        binding.layoutDaysValue.setOnClickListener {
            showDateSelect(viewModel.startUnixTime,viewModel.endUnixTime)
        }

        binding.layoutToValue.setOnClickListener {
            showDateSelect(viewModel.startUnixTime,viewModel.endUnixTime)
        }
    }

    override fun selectedDate(selectData: SelectData) {
        selectedData(selectData)
    }
}