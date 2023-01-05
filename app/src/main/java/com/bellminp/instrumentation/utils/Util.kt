package com.bellminp.instrumentation.utils

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import com.bellminp.common.timberMsg
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.model.GaugesDetailList
import com.bellminp.instrumentation.model.GaugesGroupDetailList
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

fun <T : Any?> MutableLiveData<T>.default(initialValue: T?) = apply { setValue(initialValue) }

fun <T> removeSlice(list: MutableList<T>, from: Int, end: Int) {
    for (i in end downTo from) {
        list.removeAt(i)
    }
}

fun getYear(): Int = Calendar.getInstance().get(Calendar.YEAR)

fun getMonth(): Int = Calendar.getInstance().get(Calendar.MONTH) + 1

fun getDay(): Int = Calendar.getInstance().get(Calendar.DATE)

fun dateText(value: Int) = if (value < 10) "0$value" else value.toString()

fun getUnixTime() = System.currentTimeMillis()


@SuppressLint("SimpleDateFormat")
fun convertTimestampToDateTerm(timestamp: Long): String =
    SimpleDateFormat("yyyy.MM.dd").format(timestamp)

@SuppressLint("SimpleDateFormat")
fun convertTimestampToDateRecord(timestamp: Long): String =
    SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestamp)

@SuppressLint("SimpleDateFormat")
fun convertTimestampToDateText(timestamp: Long): String =
    SimpleDateFormat("yyyy/MM/dd").format(timestamp)

@SuppressLint("SimpleDateFormat")
fun getUnixTime(date: String, fromDay: Boolean): Long {
    val formatter = SimpleDateFormat("yyyy.MM.dd.HH.mm")
    val fullDate = if (fromDay) "$date.00.00" else "$date.23.59"
    val timestamp = formatter.parse(fullDate) as Date

    return timestamp.time
}

@SuppressLint("SimpleDateFormat")
fun getUnixTime(date: String): Long {
    val formatter = SimpleDateFormat("yyyy/MM/dd")
    val timestamp = formatter.parse(date) as Date

    return timestamp.time
}

@SuppressLint("SimpleDateFormat")
fun graphLegendValue(value: Long): String {
    return SimpleDateFormat("MM/dd HH:mm").format(value)
}

@SuppressLint("SimpleDateFormat")
fun graphLegendValue2(value: Long): String {
    return SimpleDateFormat("yy/MM/dd HH:mm").format(value)
}

fun getColorType(value: Double?, gaugesDetailList: GaugesDetailList) : Int {
    if(value == null) return 0
    else{
        var type = 0
        if(gaugesDetailList.hi1enable){
            if(value >= gaugesDetailList.hi1) type = 1
        }
        if(gaugesDetailList.low1enable){
            if(value <= gaugesDetailList.low1) type = 1
        }

        if(gaugesDetailList.hi2enable){
            if(value >= gaugesDetailList.hi2) type = 2
        }
        if(gaugesDetailList.low2enable){
            if(value <= gaugesDetailList.low2) type = 2
        }

        if(gaugesDetailList.hi3enable){
            if(value >= gaugesDetailList.hi3) type = 3
        }
        if(gaugesDetailList.low3enable){
            if(value <= gaugesDetailList.low3) type = 3
        }

        return type
    }
}

fun getColorType(value: Double?, gaugesDetailList: GaugesGroupDetailList) : Int {
    if(value == null) return 0
    else{
        var type = 0
        if(gaugesDetailList.hi1enable){
            if(value >= gaugesDetailList.hi1) type = 1
        }
        if(gaugesDetailList.low1enable){
            if(value <= gaugesDetailList.low1) type = 1
        }

        if(gaugesDetailList.hi2enable){
            if(value >= gaugesDetailList.hi2) type = 2
        }
        if(gaugesDetailList.low2enable){
            if(value <= gaugesDetailList.low2) type = 2
        }

        if(gaugesDetailList.hi3enable){
            if(value >= gaugesDetailList.hi3) type = 3
        }
        if(gaugesDetailList.low3enable){
            if(value <= gaugesDetailList.low3) type = 3
        }

        return type
    }
}

fun getGraphColor(index : Int) : Int {
    return InstrumentationApplication.mInstance.resources.getIntArray(R.array.color)[index]
}

fun roundOff(value : Double) = (value * 1000).roundToInt().toDouble()/1000

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("SimpleDateFormat")
fun getDateDay(date: String, dateType: String): Int {
    val dateFormat = SimpleDateFormat(dateType)
    val nDate = dateFormat.parse(date)

    val cal = Calendar.getInstance()
    cal.time = nDate

    return cal.get(Calendar.DAY_OF_WEEK)
}