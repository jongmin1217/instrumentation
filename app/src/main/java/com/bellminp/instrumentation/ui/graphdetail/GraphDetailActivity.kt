package com.bellminp.instrumentation.ui.graphdetail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.viewModels
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.ActivityGraphDetailBinding
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.ui.base.BaseActivity
import com.bellminp.instrumentation.ui.login.LoginViewModel
import com.bellminp.instrumentation.ui.main.graph.LegendAdapter
import com.bellminp.instrumentation.utils.*
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GraphDetailActivity : BaseActivity<ActivityGraphDetailBinding,GraphDetailViewModel>(R.layout.activity_graph_detail){
    override val viewModel: GraphDetailViewModel by viewModels()

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val graphData = intent.getSerializableExtra(GRAPH_DATA) as GraphData

        when(graphData){
            is GraphType1 -> {
                binding.tvY.text = graphData.name

                binding.lineChart.apply {
                    this.xAxis.apply {

                        valueFormatter = DateAxisValueFormat()
                        position = XAxis.XAxisPosition.BOTTOM
                        setDrawGridLines(false)
                        textSize = 12f

                        setLabelCount(3, false)
                        axisMinimum = graphData.getMinTime().toFloat()
                        axisMaximum = graphData.getMaxTime().toFloat()

                    }

                    this.axisLeft.apply {
                        removeAllLimitLines()

                        if (graphData.ystep != 0.0) granularity = graphData.ystep.toFloat()

                        if (graphData.hi1enable) addLimitLine(LimitLine(graphData.hi1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                        })
                        if (graphData.hi2enable) addLimitLine(LimitLine(graphData.hi2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (graphData.hi3enable) addLimitLine(LimitLine(graphData.hi3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                        })
                        if (graphData.low1enable) addLimitLine(LimitLine(graphData.low1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                        })
                        if (graphData.low2enable) addLimitLine(LimitLine(graphData.low2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (graphData.low3enable) addLimitLine(LimitLine(graphData.low3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                        })

                        axisMaximum =
                            if (graphData.autorange) graphData.getMax().toFloat() else graphData.maxrange.toFloat()
                        axisMinimum =
                            if (graphData.autorange) graphData.getMin().toFloat() else graphData.minrange.toFloat()
                        textSize = 12f


                    }

                    axisRight.isEnabled = false
                    description.text = ""
                    setExtraOffsets(3f, 20f, 20f, 20f)
                    setPinchZoom(false)
                    isDoubleTapToZoomEnabled = false
                    legend.isEnabled = false

                    val chartData = LineData()
                    var i = 0
                    graphData.list.forEach {
                        val entryList = ArrayList<Entry>()
                        it.list.forEach { data ->
                            entryList.add(Entry(data.time.toFloat(), data.value?.toFloat() ?: 0F))
                        }
                        val set = LineDataSet(entryList, it.name)
                        set.apply {
                            axisDependency = YAxis.AxisDependency.LEFT
                            valueTextSize = 0f
                            lineWidth = 1.5f
                            setDrawCircles(true)
                            circleColors = listOf(getGraphColor(i))
                            fillAlpha = 0
                            isHighlightEnabled = false
                            setDrawValues(true)
                            color = getGraphColor(i)
                        }
                        chartData.addDataSet(set)
                        i++
                    }

                    data = chartData
                    invalidate()
                }

                FlexboxLayoutManager(binding.recyclerviewLegend.context).apply {
                    flexWrap = FlexWrap.WRAP
                    flexDirection = FlexDirection.ROW
                    justifyContent = JustifyContent.FLEX_START
                }.let {
                    binding.recyclerviewLegend.layoutManager = it
                    binding.recyclerviewLegend.adapter = LegendAdapter().apply {
                        val list = ArrayList<LegendData>()
                        for (i in graphData.list.indices) {
                            list.add(LegendData(getGraphColor(i), graphData.list[i].name))
                        }
                        submitList(list)
                    }
                }
            }
            
            is GraphType2 -> {
                binding.tvY.text = graphData.name
                
                binding.lineChart.apply {
                    this.xAxis.apply {
                        val vposList = graphData.getVpos()

                        position = XAxis.XAxisPosition.BOTTOM
                        setDrawGridLines(false)
                        textSize = 12f

                        setLabelCount(vposList.size, false)
                        axisMinimum = 0f
                        axisMaximum = graphData.getMaxVpos().toFloat()

                    }



                    this.axisLeft.apply {
                        removeAllLimitLines()



                        if (graphData.ystep != 0.0) granularity = graphData.ystep.toFloat()

                        if (graphData.hi1enable) addLimitLine(LimitLine(graphData.hi1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                        })
                        if (graphData.hi2enable) addLimitLine(LimitLine(graphData.hi2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (graphData.hi3enable) addLimitLine(LimitLine(graphData.hi3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                        })
                        if (graphData.low1enable) addLimitLine(LimitLine(graphData.low1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                        })
                        if (graphData.low2enable) addLimitLine(LimitLine(graphData.low2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (graphData.low3enable) addLimitLine(LimitLine(graphData.low3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                        })

                        axisMaximum =
                            if (graphData.autorange) graphData.getMax().toFloat() else graphData.maxrange.toFloat()
                        axisMinimum =
                            if (graphData.autorange) graphData.getMin().toFloat() else graphData.minrange.toFloat()
                        textSize = 12f
                    }

                    axisRight.isEnabled = false
                    description.text = ""
                    setExtraOffsets(3f, 20f, 20f, 20f)
                    setPinchZoom(false)
                    isDoubleTapToZoomEnabled = false
                    legend.isEnabled = false

                    val chartData = LineData()
                    var i = 0
                    graphData.list.forEach {
                        val entryList = ArrayList<Entry>()
                        entryList.add(Entry(0f, 0f))
                        it.list.forEach { data ->
                            entryList.add(Entry(data.vpos.toFloat(), data.value?.toFloat() ?: 0F))
                        }
                        val set = LineDataSet(entryList, graphLegendValue(it.time))
                        set.apply {
                            axisDependency = YAxis.AxisDependency.LEFT
                            valueTextSize = 0f
                            lineWidth = 1.5f
                            setDrawCircles(true)
                            circleColors = listOf(getGraphColor(i))
                            fillAlpha = 0
                            isHighlightEnabled = false
                            setDrawValues(true)
                            color = getGraphColor(i)
                        }
                        chartData.addDataSet(set)
                        i++
                    }

                    data = chartData
                    invalidate()
                }

                FlexboxLayoutManager(binding.recyclerviewLegend.context).apply {
                    flexWrap = FlexWrap.WRAP
                    flexDirection = FlexDirection.ROW
                    justifyContent = JustifyContent.FLEX_START
                }.let {
                    binding.recyclerviewLegend.layoutManager = it
                    binding.recyclerviewLegend.adapter = LegendAdapter().apply {
                        val list = ArrayList<LegendData>()
                        for (i in graphData.list.indices) {
                            list.add(
                                LegendData(
                                    getGraphColor(i),
                                    graphLegendValue(graphData.list[i].time)
                                )
                            )
                        }
                        submitList(list)
                    }
                }
            }
            
            is GraphType4 -> {
                binding.tvY.visibility = View.GONE

                val display = InstrumentationApplication.mInstance.resources.displayMetrics
                val width = display.widthPixels
                val height = width.toFloat() * (((graphData.yMax().toFloat() / 1000) / (graphData.xMax()
                    .toFloat() / 1000)))


                val params = FrameLayout.LayoutParams(width, height.toInt())
                binding.lineChart.layoutParams = params


                binding.lineChart.apply {

                    this.xAxis.apply {
                        position = XAxis.XAxisPosition.BOTTOM
                        textSize = 12f

                        labelCount = (graphData.xMax() / 1000) + 1
                        axisMinimum = 0f
                        axisMaximum = graphData.xMax().toFloat()
                        setDrawGridLines(true)
                        valueFormatter = Type4ValueFormat()
                    }


                    this.axisLeft.apply {
                        removeAllLimitLines()
                        valueFormatter = Type4ValueFormat()
                        if (graphData.ystep != 0.0) granularity = graphData.ystep.toFloat()

                        axisMaximum =
                            if (graphData.autorange) graphData.yMax().toFloat() else graphData.maxrange.toFloat()
                        axisMinimum = if (graphData.autorange) 0f else graphData.minrange.toFloat()
                        textSize = 12f

                        labelCount = (graphData.yMax() / 1000) + 1

                    }

                    axisRight.isEnabled = false
                    description.text = ""
                    setExtraOffsets(0f, 20f, 0f, 20f)
                    setPinchZoom(false)
                    isDoubleTapToZoomEnabled = false
                    legend.isEnabled = false

                    val chartData = LineData()
                    var i = 0
                    graphData.list.forEach {
                        val entryList = ArrayList<Entry>()
                        entryList.add(Entry(0f, 0f))

                        it.list.forEach { data ->
                            entryList.add(Entry(data.x.toFloat(), data.y.toFloat()))
                        }
                        val set = LineDataSet(entryList, graphLegendValue(it.time))
                        set.apply {
                            axisDependency = YAxis.AxisDependency.LEFT
                            if (i == graphData.list.size - 1) {
                                valueTextSize = 10f
                                valueFormatter = Type4LabelFormat(graphData.list[i])
                                valueTextColor = Color.BLUE
                            } else {
                                valueTextSize = 0f
                            }

                            lineWidth = 1.5f
                            setDrawCircles(true)
                            circleColors = listOf(getGraphColor(i))
                            fillAlpha = 0
                            isHighlightEnabled = false
                            setDrawValues(true)
                            color = getGraphColor(i)
                        }
                        chartData.addDataSet(set)
                        i++
                    }

                    data = chartData
                    invalidate()
                }

                FlexboxLayoutManager(binding.recyclerviewLegend.context).apply {
                    flexWrap = FlexWrap.WRAP
                    flexDirection = FlexDirection.ROW
                    justifyContent = JustifyContent.FLEX_START
                }.let {
                    binding.recyclerviewLegend.layoutManager = it
                    binding.recyclerviewLegend.adapter = LegendAdapter().apply {
                        val list = ArrayList<LegendData>()
                        for (i in graphData.list.indices) {
                            list.add(
                                LegendData(
                                    getGraphColor(i),
                                    graphLegendValue(graphData.list[i].time)
                                )
                            )
                        }
                        submitList(list)
                    }
                }
            }
        }
    }
}