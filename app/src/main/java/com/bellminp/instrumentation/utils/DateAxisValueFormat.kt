package com.bellminp.instrumentation.utils

import android.annotation.SuppressLint
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat

class DateAxisValueFormat : IndexAxisValueFormatter() {

    @SuppressLint("SimpleDateFormat")
    override fun getFormattedValue(value: Float): String {
        return SimpleDateFormat("yyyy/MM/dd\nHH:mm").format(value)
    }
}