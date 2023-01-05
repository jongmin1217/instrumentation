package com.bellminp.instrumentation.ui.main.graph

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.updateLayoutParams
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.*
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.ui.base.BaseListAdapter
import com.bellminp.instrumentation.ui.base.BaseViewHolder
import com.bellminp.instrumentation.ui.main.tree.TreeAdapter
import com.bellminp.instrumentation.utils.*
import com.bellminp.instrumentation.utils.ext.margin
import com.ddd.androidutils.DoubleClick
import com.ddd.androidutils.DoubleClickListener
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.EntryXComparator
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class GraphAdapter(
    private val fragHeight : Int,
    private val onItemClick: ((item: GraphData) -> Unit)
) : BaseListAdapter<GraphData>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<GraphData> {
        return when (viewType) {
            TYPE1 -> {
                Type1Holder(
                    ItemGraphType1Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onItemClick
                )
            }

            TYPE2 -> {
                Type2Holder(
                    ItemGraphType2Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onItemClick
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

            TYPE4 -> {
                Type4Holder(
                    ItemGraphType4Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onItemClick
                )
            }

            TYPE5 -> {
                Type5Holder(
                    ItemGraphType5Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    fragHeight
                )
            }

            TYPE_LEGEND -> {
                LegendHolder(
                    ItemGraphLegendBinding.inflate(
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
                    ),
                    onItemClick
                )
            }
        }
    }

    override fun getItemViewType(position: Int) = when (currentList[position]) {
        is GraphType1 -> TYPE1
        is GraphType2 -> TYPE2
        is GraphType3 -> TYPE3
        is GraphType4 -> TYPE4
        is GraphType5 -> TYPE5
        is GraphLegend -> TYPE_LEGEND
        else -> throw IllegalStateException("can't find view type")
    }

    class Type1Holder(
        private val binding: ItemGraphType1Binding,
        private val onItemClick: ((item: GraphData) -> Unit)
    ) : BaseViewHolder<GraphData>(binding) {

        override fun bind(item: GraphData) {
            if (item is GraphType1) {
                binding.item = item
                binding.executePendingBindings()
                binding.lineChart.fitScreen()

                binding.lineChart.setOnClickListener(DoubleClick(object : DoubleClickListener {
                    override fun onSingleClickEvent(view: View?) {}

                    override fun onDoubleClickEvent(view: View?) {
                        onItemClick(item)
                    }
                }))



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

                        if (item.hi1enable) addLimitLine(LimitLine(item.hi1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                            label = "1차(${item.hi1})"
                            textColor = Color.BLUE
                        })
                        if (item.hi2enable) addLimitLine(LimitLine(item.hi2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                            label = "2차(${item.hi2})"
                            textColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (item.hi3enable) addLimitLine(LimitLine(item.hi3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                            label = "3차(${item.hi3})"
                            textColor = Color.RED
                        })
                        if (item.low1enable) addLimitLine(LimitLine(item.low1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                            label = "1차(${item.low1})"
                            textColor = Color.BLUE
                            labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM

                        })
                        if (item.low2enable) addLimitLine(LimitLine(item.low2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                            label = "2차(${item.low2})"
                            textColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                            labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
                        })
                        if (item.low3enable) addLimitLine(LimitLine(item.low3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                            label = "3차(${item.low3})"
                            textColor = Color.RED
                            labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
                        })

                        axisMaximum =
                            if (item.autorange) item.getMax().toFloat() else item.maxrange.toFloat()
                        axisMinimum =
                            if (item.autorange) item.getMin().toFloat() else item.minrange.toFloat()
                        textSize = 12f

                        if (item.ystep != 0.0) {
                            granularity = item.ystep.toFloat()
                            setLabelCount((((axisMaximum / item.ystep) * 2) + 1).toInt(), true)
                        }
                    }

                    axisRight.isEnabled = false
                    description.text = ""
                    setExtraOffsets(16f, 0f, 16f, 0f)
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

                            when(InstrumentationApplication.mInstance.graphPoint){
                                0 -> {
                                    setDrawCircles(true)
                                    setDrawCircleHole(false)
                                }
                                1 -> {
                                    setDrawCircles(true)
                                    setDrawCircleHole(true)
                                }
                                2 -> {
                                    setDrawCircles(false)
                                }
                            }

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
                        for (i in item.list.indices) {
                            list.add(LegendData(getGraphColor(i), text = item.list[i].name))
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
        private val binding: ItemGraphType2Binding,
        private val onItemClick: ((item: GraphData) -> Unit)
    ) : BaseViewHolder<GraphData>(binding) {

        override fun bind(item: GraphData) {
            if (item is GraphType2) {
                binding.item = item
                binding.executePendingBindings()
                binding.lineChart.fitScreen()

                binding.lineChart.setOnClickListener(DoubleClick(object : DoubleClickListener {
                    override fun onSingleClickEvent(view: View?) {}

                    override fun onDoubleClickEvent(view: View?) {
                        onItemClick(item)
                    }
                }))



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


                        if (item.hi1enable) addLimitLine(LimitLine(item.hi1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                            label = "1차(${item.hi1})"
                            textColor = Color.BLUE
                        })
                        if (item.hi2enable) addLimitLine(LimitLine(item.hi2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                            label = "2차(${item.hi2})"
                            textColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (item.hi3enable) addLimitLine(LimitLine(item.hi3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                            label = "3차(${item.hi3})"
                            textColor = Color.RED
                        })
                        if (item.low1enable) addLimitLine(LimitLine(item.low1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                            label = "1차(${item.low1})"
                            textColor = Color.BLUE
                            labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM

                        })
                        if (item.low2enable) addLimitLine(LimitLine(item.low2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                            label = "2차(${item.low2})"
                            textColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                            labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
                        })
                        if (item.low3enable) addLimitLine(LimitLine(item.low3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                            label = "3차(${item.low3})"
                            textColor = Color.RED
                            labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
                        })

                        axisMaximum =
                            if (item.autorange) item.getMax().toFloat() else item.maxrange.toFloat()
                        axisMinimum =
                            if (item.autorange) item.getMin().toFloat() else item.minrange.toFloat()
                        textSize = 12f

                        if (item.ystep != 0.0) {
                            granularity = item.ystep.toFloat()
                            setLabelCount((((axisMaximum / item.ystep) * 2) + 1).toInt(), true)
                        }
                    }

                    axisRight.isEnabled = false
                    description.text = ""
                    setExtraOffsets(16f, 0f, 16f, 0f)
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
                            when(InstrumentationApplication.mInstance.graphPoint){
                                0 -> {
                                    setDrawCircles(true)
                                    setDrawCircleHole(false)
                                }
                                1 -> {
                                    setDrawCircles(true)
                                    setDrawCircleHole(true)
                                }
                                2 -> {
                                    setDrawCircles(false)
                                }
                            }
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
                binding.lineChart.fitScreen()



                binding.lineChart.apply {
                    val display = InstrumentationApplication.mInstance.resources.displayMetrics
                    val width = display.widthPixels

                    val density = display.density

                    val offset = (width/density)
                    val params = FrameLayout.LayoutParams((width * 1.7).toInt(), (width * 1.7).toInt())
                    layoutParams = params

                    this.xAxis.apply {


                        position = XAxis.XAxisPosition.TOP
                        setDrawGridLines(false)
                        textSize = 12f

                        labelCount = item.getVpos().size
                        axisMinimum = item.getMinVpos().toFloat()
                        axisMaximum = item.getMaxVpos().toFloat()
                        setDrawGridLines(true)

                        labelRotationAngle = 90f


                    }


                    this.axisLeft.apply {
                        removeAllLimitLines()


                        if (item.hi1enable) addLimitLine(LimitLine(item.hi1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                            label = "1차(${item.hi1})"
                            textColor = Color.BLUE
                            labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
                        })
                        if (item.hi2enable) addLimitLine(LimitLine(item.hi2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                            label = "2차(${item.hi2})"
                            textColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                            labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
                        })
                        if (item.hi3enable) addLimitLine(LimitLine(item.hi3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                            label = "3차(${item.hi3})"
                            textColor = Color.RED
                            labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
                        })
                        if (item.low1enable) addLimitLine(LimitLine(item.low1.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.BLUE
                            label = "1차(${item.low1})"
                            textColor = Color.BLUE

                        })
                        if (item.low2enable) addLimitLine(LimitLine(item.low2.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                            label = "2차(${item.low2})"
                            textColor = InstrumentationApplication.mInstance.resources.getColor(
                                R.color.orange,
                                null
                            )
                        })
                        if (item.low3enable) addLimitLine(LimitLine(item.low3.toFloat()).apply {
                            lineWidth = 1f
                            enableDashedLine(10f, 10f, 10f)
                            lineColor = Color.RED
                            label = "3차(${item.low3})"
                            textColor = Color.RED
                        })

                        isInverted = true
                        axisMaximum =
                            if (item.autorange) item.getMax().toFloat() else item.maxrange.toFloat()
                        axisMinimum =
                            if (item.autorange) item.getMin().toFloat() else item.minrange.toFloat()
                        textSize = 12f

                        labelCount = 3

                        if (item.ystep != 0.0) {
                            granularity = item.ystep.toFloat()
                            setLabelCount((((axisMaximum / item.ystep) * 2) + 1).toInt(), true)
                        }
                        //isKeepPositionOnRotation = true

                    }

                    axisRight.isEnabled = false
                    description.text = ""
                    setExtraOffsets(0f, 16f, 40f, (offset*0.7).toFloat()+20f)
                    setPinchZoom(false)
                    isDoubleTapToZoomEnabled = false
                    legend.isEnabled = false

                    val chartData = LineData()
                    var i = 0
                    item.list.forEach {
                        val entryList = ArrayList<Entry>()

                        try {
                            val initVpos = it.list[0].vpos + (it.list[0].vpos - it.list[1].vpos)
                            entryList.add(Entry(initVpos.toFloat(), 0f))

                        } catch (e: Exception) {
                        }

                        it.list.forEach { data ->
                            entryList.add(Entry(data.vpos.toFloat(), data.value?.toFloat() ?: 0F))
                        }
                        val set = LineDataSet(entryList, graphLegendValue(it.time))
                        set.apply {
                            axisDependency = YAxis.AxisDependency.LEFT
                            valueTextSize = 0f
                            lineWidth = 1.5f
                            when(InstrumentationApplication.mInstance.graphPoint){
                                0 -> {
                                    setDrawCircles(true)
                                    setDrawCircleHole(false)
                                }
                                1 -> {
                                    setDrawCircles(true)
                                    setDrawCircleHole(true)
                                }
                                2 -> {
                                    setDrawCircles(false)
                                }
                            }
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

            }
        }

        override fun recycle() {
        }
    }


    class Type4Holder(
        private val binding: ItemGraphType4Binding,
        private val onItemClick: ((item: GraphData) -> Unit)
    ) : BaseViewHolder<GraphData>(binding) {

        override fun bind(item: GraphData) {
            if (item is GraphType4) {
                binding.item = item
                binding.executePendingBindings()
                binding.lineChart.fitScreen()

                binding.lineChart.setOnClickListener(DoubleClick(object : DoubleClickListener {
                    override fun onSingleClickEvent(view: View?) {}

                    override fun onDoubleClickEvent(view: View?) {
                        onItemClick(item)
                    }
                }))

                binding.tvScale.text = String.format("변위값 scale = %d",SCALE)

                val display = InstrumentationApplication.mInstance.resources.displayMetrics
                val width = display.widthPixels
                val height = width.toFloat() * (((item.yMax().toFloat() / 1000) / (item.xMax()
                    .toFloat() / 1000)))


                val params = FrameLayout.LayoutParams(width, height.toInt())
                binding.lineChart.layoutParams = params


                binding.lineChart.apply {

                    this.xAxis.apply {
                        position = XAxis.XAxisPosition.BOTTOM
                        textSize = 12f

                        labelCount = (item.xMax() / 1000) + 1
                        axisMinimum = 0f
                        axisMaximum = item.xMax().toFloat()
                        setDrawGridLines(true)
                        valueFormatter = Type4ValueFormat()
                    }


                    this.axisLeft.apply {
                        removeAllLimitLines()
                        valueFormatter = Type4ValueFormat()
                        if (item.ystep != 0.0) granularity = item.ystep.toFloat()

                        axisMaximum =
                            if (item.autorange) item.yMax().toFloat() else item.maxrange.toFloat()
                        axisMinimum = if (item.autorange) 0f else item.minrange.toFloat()
                        textSize = 12f

                        labelCount = (item.yMax() / 1000) + 1

                    }

                    axisRight.isEnabled = false
                    description.text = ""
                    setExtraOffsets(16f, 0f, 16f, 0f)
                    setPinchZoom(false)
                    isDoubleTapToZoomEnabled = false
                    legend.isEnabled = false

                    val chartData = LineData()
                    var i = 0
                    item.list.forEach {
                        val entryList = ArrayList<Entry>()
                        entryList.add(Entry(0f, 0f))

                        it.list.forEach { data ->
                            entryList.add(Entry(data.x.toFloat(), data.y.toFloat()))
                        }
                        val set = LineDataSet(entryList, graphLegendValue(it.time))
                        set.apply {
                            axisDependency = YAxis.AxisDependency.LEFT
                            if (i == item.list.size - 1) {
                                setDrawValues(true)
                                valueTextSize = 10f
                                valueFormatter = Type4LabelFormat(item.list[i])
                                valueTextColor = getGraphColor(i)
                            } else {
                                setDrawValues(false)
                                valueTextSize = 0f
                            }

                            lineWidth = 1.5f
                            when(InstrumentationApplication.mInstance.graphPoint){
                                0 -> {
                                    setDrawCircles(true)
                                    setDrawCircleHole(false)
                                }
                                1 -> {
                                    setDrawCircles(true)
                                    setDrawCircleHole(true)
                                }
                                2 -> {
                                    setDrawCircles(false)
                                }
                            }
                            circleColors = listOf(getGraphColor(i))
                            fillAlpha = 0
                            isHighlightEnabled = false
                            setDrawValues(true)
                            color = getGraphColor(i)
                        }
                        chartData.addDataSet(set)
                        i++
                    }

//                    item.standardList.forEach {
//                        val entryList = ArrayList<Entry>()
//                        //entryList.add(Entry(0f, 0f))
//                        it.list.forEach { data->
//                            entryList.add(Entry(data.x.toFloat(), data.y.toFloat()))
//                        }
//                        val set = LineDataSet(entryList, it.type.toString())
//                        set.apply {
//                            enableDashedLine(10f, 10f, 10f)
//                            axisDependency = YAxis.AxisDependency.LEFT
//                            valueTextSize = 0f
//                            lineWidth = 1.5f
//                            setDrawCircles(false)
//                            setDrawCircleHole(false)
//                            circleColors = listOf(getGraphColor(i))
//                            fillAlpha = 0
//                            isHighlightEnabled = false
//                            setDrawValues(false)
//                            color = when(it.type){
//                                0,3 -> Color.BLUE
//                                1,4 -> InstrumentationApplication.mInstance.resources.getColor(R.color.orange,null)
//                                else -> Color.RED
//                            }
//                        }
//                        chartData.addDataSet(set)
//                    }

                    data = chartData
                    invalidate()
                }

            }
        }

        override fun recycle() {
        }
    }

    class Type5Holder(
        private val binding: ItemGraphType5Binding,
        private val fragHeight : Int
    ) : BaseViewHolder<GraphData>(binding) {

        override fun bind(item: GraphData) {
            if (item is GraphType5) {
                binding.item = item
                binding.executePendingBindings()

                val display = InstrumentationApplication.mInstance.resources.displayMetrics
                val width = display.widthPixels



                binding.view.setOnClickListener { }

                binding.radarChart.apply {
                    description.text = ""
                    setExtraOffsets(16f, 16f, 16f, 16f)
                    //setDrawWeb(false)
                    this.xAxis.apply {
                        position = XAxis.XAxisPosition.BOTTOM
                        axisMinimum = 0f
                        axisMaximum = 360f
                        setDrawGridLines(false)
                        setDrawLabels(false)

                    }

                    this.yAxis.apply {
                        val max = if (item.getMax() == 0.0) 1f else item.getMax().toFloat()
                        setLabelCount(5, true)
                        axisMaximum = max
                        axisMinimum = 0f
                        setDrawLabels(false)
                        setDrawGridLines(false)

                        val directionList = ArrayList<Direction>()
                        val range = max / 4
                        for (i in 0..3) {
                            directionList.add(
                                Direction(
                                    String.format("%.1f", -(max - (range * i))),
                                    if (i == 0) 0 else 1,
                                    item.chartType
                                )
                            )
                        }
                        directionList.add(
                            Direction(
                                "0.0",
                                1,
                                item.chartType
                            )
                        )
                        for (i in 3 downTo 0) {
                            directionList.add(
                                Direction(
                                    String.format("%.1f", (max - (range * i))),
                                    if (i == 0) 2 else 1,
                                    item.chartType
                                )
                            )
                        }


                        val directionAdapter =
                            DirectionAdapter().apply { submitList(directionList) }
                        binding.recyclerviewDirection.adapter = directionAdapter
                    }
                    legend.isEnabled = false

                    val chartData = RadarData()

                    var i = 0
                    item.list.forEach {

                        val entryList = ArrayList<RadarEntry>()
                        it.list.forEach { data ->
                            entryList.add(RadarEntry(data.y.toFloat()))
                        }
                        val set = RadarDataSet(entryList, graphLegendValue(it.time))
                        set.apply {
                            axisDependency = YAxis.AxisDependency.LEFT
                            valueTextSize = 0f
                            lineWidth = 2f
                            setDrawMarkers(true)
                            setDrawIcons(true)

//                        circleColors = listOf(getGraphColor(i))
//                        fillAlpha = 0
//                        isHighlightEnabled = false
//                        setDrawValues(true)
                            color = getGraphColor(i)
                        }
                        chartData.addDataSet(set)
                        i++
                    }

                    data = chartData
                    invalidate()

                }

                if(fragHeight < width){
                    val params = ConstraintLayout.LayoutParams(fragHeight - 400, fragHeight - 400)
                    params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    binding.radarChart.layoutParams = params

                    val params2 = ConstraintLayout.LayoutParams(fragHeight - 400, binding.recyclerviewDirection.height)
                    params2.topToBottom = binding.radarChart.id
                    params2.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    params2.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    binding.recyclerviewDirection.layoutParams = params2

                    val params3 = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
                    params3.topToBottom = binding.recyclerviewDirection.id
                    params3.endToEnd = binding.recyclerviewDirection.id
                    binding.tvX.layoutParams = params3
                }

            }
        }

        override fun recycle() {
        }
    }

    class LegendHolder(
        private val binding: ItemGraphLegendBinding
    ) : BaseViewHolder<GraphData>(binding) {

        override fun bind(item: GraphData) {
            if (item is GraphLegend) {
                binding.item = item
                binding.executePendingBindings()

                FlexboxLayoutManager(binding.recyclerviewLegend.context).apply {
                    flexWrap = FlexWrap.WRAP
                    flexDirection = FlexDirection.ROW
                    justifyContent = JustifyContent.FLEX_START
                }.let {
                    binding.recyclerviewLegend.layoutManager = it
                    binding.recyclerviewLegend.adapter = LegendAdapter().apply {
                        submitList(item.list)
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
        const val TYPE4 = 3
        const val TYPE5 = 4
        const val TYPE_LEGEND = 5
    }
}