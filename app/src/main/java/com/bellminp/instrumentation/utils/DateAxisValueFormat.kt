package com.bellminp.instrumentation.utils

import android.annotation.SuppressLint
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat

class DateAxisValueFormat : IndexAxisValueFormatter() {

    @SuppressLint("SimpleDateFormat")
    override fun getFormattedValue(value: Float): String {
        return SimpleDateFormat("MM/dd HH:mm").format(value)
    }
}