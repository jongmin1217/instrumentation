package com.bellminp.instrumentation.ui.main.graph

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
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
import com.bellminp.instrumentation.utils.graphXValue
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.MPPointF

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
                        val timeList = item.getTime()

                        valueFormatter = DateAxisValueFormat()
                        position = XAxis.XAxisPosition.BOTTOM
                        setDrawGridLines(false)
                        textSize = 8f

                        setLabelCount(if(timeList.size>3) 3 else timeList.size,false)
                        axisMinimum = item.getMinTime().toFloat()
                        axisMaximum = item.getMaxTime().toFloat()

                    }



                    this.axisLeft.apply {
                        removeAllLimitLines()



                        if(item.ystep != 0.0) granularity = item.ystep.toFloat()

                        if (item.hi1enable) addLimitLine(LimitLine(item.hi1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f,10f,10f)
                            lineColor = Color.BLUE
                        })
                        if (item.hi2enable) addLimitLine(LimitLine(item.hi2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f,10f,10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (item.hi3enable) addLimitLine(LimitLine(item.hi3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f,10f,10f)
                            lineColor = Color.RED
                        })
                        if (item.low1enable) addLimitLine(LimitLine(item.low1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f,10f,10f)
                            lineColor = Color.BLUE
                        })
                        if (item.low2enable) addLimitLine(LimitLine(item.low2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f,10f,10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (item.low3enable) addLimitLine(LimitLine(item.low3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f,10f,10f)
                            lineColor = Color.RED
                        })

                        axisMaximum = if(item.autorange) item.getMax().toFloat() else item.maxrange.toFloat()
                        axisMinimum = if(item.autorange) item.getMin().toFloat() else item.minrange.toFloat()
                    }

                    axisRight.isEnabled = false
                    description.text = ""
                    setExtraOffsets(3f, 20f, 20f, 0f)
                    setPinchZoom(false)
                    isDoubleTapToZoomEnabled = false
                    legend.apply {
                        verticalAlignment = Legend.LegendVerticalAlignment.TOP
                        horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                        orientation = Legend.LegendOrientation.HORIZONTAL
                        setDrawInside(false)
                    }

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
                            setDrawCircles(false)
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

            }
        }

        override fun recycle() {
        }
    }

    companion object {
        const val TYPE1 = 0
    }
}