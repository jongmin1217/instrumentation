package com.bellminp.instrumentation.ui.main.graph

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.core.view.marginEnd
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.*
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder
import com.bellminp.instrumentation.ui.main.tree.TreeAdapter
import com.bellminp.instrumentation.utils.DateAxisValueFormat
import com.bellminp.instrumentation.utils.getGraphColor
import com.bellminp.instrumentation.utils.graphLegendValue
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.EntryXComparator
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import java.util.*
import kotlin.collections.ArrayList

class GraphAdapter : BaseListAdapter<GraphData>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<GraphData> {
        return when (viewType) {
            TYPE1 -> {
                Type1Holder(
                    ItemGraphType1Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            TYPE2 -> {
                Type2Holder(
                    ItemGraphType2Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            TYPE3 -> {
                Type3Holder(
                    ItemGraphType3Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                Type1Holder(
                    ItemGraphType1Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int) = when (currentList[position]) {
        is GraphType1 -> TYPE1
        is GraphType2 -> TYPE2
        is GraphType3 -> TYPE3
        else -> throw IllegalStateException("can't find view type")
    }

    class Type1Holder(
        private val binding: ItemGraphType1Binding
    ) : BaseViewHolder<GraphData>(binding) {

        override fun bind(item: GraphData) {
            if (item is GraphType1) {
                binding.item = item
                binding.executePendingBindings()

                binding.lineChart.apply {
                    this.xAxis.apply {

                        valueFormatter = DateAxisValueFormat()
                        position = XAxis.XAxisPosition.BOTTOM
                        setDrawGridLines(false)
                        textSize = 12f

                        setLabelCount(3, false)
                        axisMinimum = item.getMinTime().toFloat()
                        axisMaximum = item.getMaxTime().toFloat()



                    }



                    this.axisLeft.apply {
                        removeAllLimitLines()



                        if (item.ystep != 0.0) granularity = item.ystep.toFloat()

                        if (item.hi1enable) addLimitLine(LimitLine(item.hi1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                        })
                        if (item.hi2enable) addLimitLine(LimitLine(item.hi2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (item.hi3enable) addLimitLine(LimitLine(item.hi3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                        })
                        if (item.low1enable) addLimitLine(LimitLine(item.low1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                        })
                        if (item.low2enable) addLimitLine(LimitLine(item.low2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (item.low3enable) addLimitLine(LimitLine(item.low3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                        })

                        axisMaximum =
                            if (item.autorange) item.getMax().toFloat() else item.maxrange.toFloat()
                        axisMinimum =
                            if (item.autorange) item.getMin().toFloat() else item.minrange.toFloat()
                        textSize = 12f


                    }

                    axisRight.isEnabled = false
                    description.text = ""
                    setExtraOffsets(3f, 20f, 20f, 0f)
                    setPinchZoom(false)
                    isDoubleTapToZoomEnabled = false
                    legend.isEnabled = false

                    val chartData = LineData()
                    var i = 0
                    item.list.forEach {
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
                        for(i in item.list.indices){
                            list.add(LegendData(getGraphColor(i),item.list[i].name))
                        }
                        submitList(list)
                    }
                }

            }
        }

        override fun recycle() {
        }
    }

    class Type2Holder(
        private val binding: ItemGraphType2Binding
    ) : BaseViewHolder<GraphData>(binding) {

        override fun bind(item: GraphData) {
            if (item is GraphType2) {
                binding.item = item
                binding.executePendingBindings()

                binding.lineChart.apply {
                    this.xAxis.apply {
                        val vposList = item.getVpos()

                        position = XAxis.XAxisPosition.BOTTOM
                        setDrawGridLines(false)
                        textSize = 12f

                        setLabelCount(vposList.size, false)
                        axisMinimum = 0f
                        axisMaximum = item.getMaxVpos().toFloat()

                    }



                    this.axisLeft.apply {
                        removeAllLimitLines()



                        if (item.ystep != 0.0) granularity = item.ystep.toFloat()

                        if (item.hi1enable) addLimitLine(LimitLine(item.hi1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                        })
                        if (item.hi2enable) addLimitLine(LimitLine(item.hi2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (item.hi3enable) addLimitLine(LimitLine(item.hi3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                        })
                        if (item.low1enable) addLimitLine(LimitLine(item.low1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                        })
                        if (item.low2enable) addLimitLine(LimitLine(item.low2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (item.low3enable) addLimitLine(LimitLine(item.low3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                        })

                        axisMaximum =
                            if (item.autorange) item.getMax().toFloat() else item.maxrange.toFloat()
                        axisMinimum =
                            if (item.autorange) item.getMin().toFloat() else item.minrange.toFloat()
                        textSize = 12f
                    }

                    axisRight.isEnabled = false
                    description.text = ""
                    setExtraOffsets(3f, 20f, 20f, 0f)
                    setPinchZoom(false)
                    isDoubleTapToZoomEnabled = false
                    legend.isEnabled = false

                    val chartData = LineData()
                    var i = 0
                    item.list.forEach {
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
                        for(i in item.list.indices){
                            list.add(LegendData(getGraphColor(i),graphLegendValue(item.list[i].time)))
                        }
                        submitList(list)
                    }
                }

            }
        }

        override fun recycle() {
        }
    }

    class Type3Holder(
        private val binding: ItemGraphType3Binding
    ) : BaseViewHolder<GraphData>(binding) {

        override fun bind(item: GraphData) {
            if (item is GraphType3) {
                binding.item = item
                binding.executePendingBindings()


                binding.lineChart.apply {

                    this.xAxis.apply {


                        position = XAxis.XAxisPosition.TOP
                        setDrawGridLines(false)
                        textSize = 12f

                        labelCount = item.getVpos().size - 1
                        axisMinimum = item.getMinVpos().toFloat()
                        axisMaximum = item.getMaxVpos().toFloat()
                        setDrawGridLines(true)

                        labelRotationAngle = 90f


                    }


                    this.axisLeft.apply {
                        removeAllLimitLines()


                        if (item.ystep != 0.0) granularity = item.ystep.toFloat()

                        if (item.hi1enable) addLimitLine(LimitLine(item.hi1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                        })
                        if (item.hi2enable) addLimitLine(LimitLine(item.hi2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (item.hi3enable) addLimitLine(LimitLine(item.hi3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                        })
                        if (item.low1enable) addLimitLine(LimitLine(item.low1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                        })
                        if (item.low2enable) addLimitLine(LimitLine(item.low2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (item.low3enable) addLimitLine(LimitLine(item.low3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                        })
                        isInverted = true
                        axisMaximum =
                            if (item.autorange) item.getMax().toFloat() else item.maxrange.toFloat()
                        axisMinimum =
                            if (item.autorange) item.getMin().toFloat() else item.minrange.toFloat()
                        textSize = 12f

                        labelCount = 3
                        //isKeepPositionOnRotation = true

                    }

                    axisRight.isEnabled = false
                    description.text = ""
                    setExtraOffsets(0f, 50f, 0f, 50f)
                    setPinchZoom(false)
                    isDoubleTapToZoomEnabled = false
                    legend.isEnabled = false

                    val chartData = LineData()
                    var i = 0
                    item.list.forEach {
                        val entryList = ArrayList<Entry>()

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
                        for(i in item.list.indices){
                            list.add(LegendData(getGraphColor(i),graphLegendValue(item.list[i].time)))
                        }
                        submitList(list)
                    }
                }

            }
        }

        override fun recycle() {
        }
    }

    companion object {
        const val TYPE1 = 0
        const val TYPE2 = 1
        const val TYPE3 = 2
    }
}