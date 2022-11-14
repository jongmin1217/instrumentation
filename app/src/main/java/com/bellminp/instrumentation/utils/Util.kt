package com.bellminp.instrumentation.utils

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.bellminp.common.timberMsg
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

fun <T : Any?> MutableLiveData<T>.default(initialValue: T?) = apply { setValue(initialValue) }

fun <T> removeSlice(list: MutableList<T>, from: Int, end: Int) {
    for (i in end downTo from) {
        list.removeAt(i)
    }
}

fun getYear(): Int = Calendar.getInstance().get(Calendar.YEAR)

fun getMonth(): Int = Calendar.getInstance().get(Calendar.MONTH) + 1

fun getDay(): Int = Calendar.getInstance().get(Calendar.DATE)

fun dateText(value : Int) = if (value < 10) "0$value" else value.toString()

fun getUnixTime() = System.currentTimeMillis()


@SuppressLint("SimpleDateFormat")
fun convertTimestampToDateTerm(timestamp: Long): String = SimpleDateFormat("yyyy.MM.dd").format(timestamp)

@SuppressLint("SimpleDateFormat")
fun convertTimestampToDateText(timestamp: Long): String = SimpleDateFormat("MM / dd").format(timestamp)

@SuppressLint("SimpleDateFormat")
fun getUnixTime(date : String, fromDay : Boolean) : Long{
    val formatter = SimpleDateFormat("yyyy.MM.dd.HH.mm")
    val fullDate = if(fromDay) "$date.00.00" else "$date.23.59"
    val timestamp = formatter.parse(fullDate) as Date

    return timestamp.time
}