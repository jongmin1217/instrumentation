package com.bellminp.instrumentation.ui.main.graph

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.FragmentGraphBinding
import com.bellminp.instrumentation.mapper.*
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.ui.base.BaseFragment
import com.bellminp.instrumentation.ui.graphdetail.GraphDetailActivity
import com.bellminp.instrumentation.ui.main.tree.TreeViewModel
import com.bellminp.instrumentation.utils.GRAPH_DATA
import com.bellminp.instrumentation.utils.getUnixTime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GraphFragment(
    private val selectData: SelectData,
    private val gaugesData: GaugesData?,
    private val fragHeight : Int,
    private val selectedData: ((type: Int, text: String, time: Long) -> Unit)
) : BaseFragment<FragmentGraphBinding, GraphViewModel>(R.layout.fragment_graph) {
    override val viewModel by activityViewModels<GraphViewModel>()

    private val adapter = GraphAdapter(fragHeight) { data ->
        activity?.let { activity ->
            Intent(activity, GraphDetailActivity::class.java).also {
                it.putExtra(GRAPH_DATA, data)
                startActivity(it)
            }
        }
    }

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

    fun settingGraphData(data: GaugesData?) {
        viewModel.gaugesData.value = data
        binding.scrollView.scrollTo(0,0)
        when (data) {
            is GaugesDetail -> {
                when (data.chartType) {
                    5, 6 -> adapter.submitList(data.dataToGraph5())
                    7 -> adapter.submitList(data.dataToGraph7())
                    else -> adapter.submitList(data.dataToGraph())
                }
            }
            is GaugesGroupDetail -> {
                when (data.chartType) {
                    2 -> adapter.submitList(data.dataToGraph())
                    3 -> adapter.submitList(data.dataToGraph3())
                    4 -> adapter.submitList(data.dataToGraph4())
                }
            }
        }

    }

    fun setSelectData(selectData: SelectData) {
        with(viewModel) {
            fromDay.value = selectData.fromDay
            days.value = selectData.days
            toDay.value = selectData.toDay
            selectSections.value = selectData.selectSections
            selectGauges.value = selectData.selectGauges

            startUnixTime = selectData.startUnixTime
            endUnixTime = selectData.endUnixTime
        }
    }

    private fun initAdapter() {
        binding.recyclerviewGraph.adapter = adapter
    }

    private fun initLayout() {
        setSelectData(selectData)
    }

    private fun initListener() {
        binding.layoutFrom.setOnClickListener {
            selectDateShow(
                null,
                getUnixTime(viewModel.toDay.value ?: ""),
                getUnixTime(viewModel.fromDay.value ?: ""),
                0
            )
        }

        binding.layoutTo.setOnClickListener {
            selectDateShow(
                getUnixTime(viewModel.fromDay.value ?: ""),
                getUnixTime(),
                getUnixTime(viewModel.toDay.value ?: ""),
                1
            )
        }
    }

    override fun selectedDate(type: Int, text: String, time: Long) {
        selectedData(type, text, time)
    }
}