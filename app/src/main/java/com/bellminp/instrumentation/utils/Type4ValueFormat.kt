package com.bellminp.instrumentation.utils

import android.annotation.SuppressLint
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat

class Type4ValueFormat : IndexAxisValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return ((value.toInt())/1000).toString()
    }
}