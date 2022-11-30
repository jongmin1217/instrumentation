package com.bellminp.instrumentation.utils

import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.model.GraphGroupPointType4
import com.bellminp.instrumentation.model.GraphPointType4
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class Type4LabelFormat(private val list : GraphGroupPointType4) : IndexAxisValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        timberMsg(value)
        val item = list.list.filter { it.y.toFloat() == value }
        val data = try {
            item[0]
        }catch (e : IndexOutOfBoundsException){
            GraphPointType4(0.0,0.0)
        }
        return "(${roundOff(data.initX?:0.0)}, ${roundOff(data.initY?:0.0)})"
    }


}