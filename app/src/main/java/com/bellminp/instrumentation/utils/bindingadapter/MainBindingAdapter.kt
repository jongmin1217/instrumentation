package com.bellminp.instrumentation.utils.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.model.CellData
import java.text.DecimalFormat

@BindingAdapter("setPhoneNumber")
fun setPhoneNumber(tv: TextView, item: String) {
    val dec = DecimalFormat("###,####,####")

    tv.text = if(item.isNotEmpty()) "핸드폰: 0"+dec.format(item.toInt()).replace(",","-") else "핸드폰:"
}