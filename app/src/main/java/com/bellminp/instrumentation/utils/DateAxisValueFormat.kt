package com.bellminp.instrumentation.utils

import android.annotation.SuppressLint
import com.bellminp.instrumentation.InstrumentationApplication
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat

class DateAxisValueFormat : IndexAxisValueFormatter() {

    @SuppressLint("SimpleDateFormat")
    override fun getFormattedValue(value: Float): String {
        return if(InstrumentationApplication.mInstance.graphDate == 0) SimpleDateFormat("MM/dd HH:mm").format(value)
        else SimpleDateFormat("yy/MM/dd HH:mm").format(value)
    }
}