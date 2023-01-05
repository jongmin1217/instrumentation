package com.bellminp.instrumentation.utils.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.model.LegendData
import com.bellminp.instrumentation.model.SitesList
import com.bellminp.instrumentation.utils.graphLegendValue
import com.bellminp.instrumentation.utils.graphLegendValue2

@BindingAdapter("setLegendText")
fun setLegendText(tv: TextView, item: LegendData) {
    val text = if(item.time != null){
        if(InstrumentationApplication.mInstance.graphDate == 0) graphLegendValue(item.time) else graphLegendValue2(item.time)
    }else{
        item.text
    }

    tv.text = text
}