package com.bellminp.instrumentation.utils.bindingadapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.model.CellData
import java.text.DecimalFormat

@BindingAdapter("setPhoneNumber")
fun setPhoneNumber(tv: TextView, item: String) {
    val dec = DecimalFormat("###,####,####")

    tv.text = if(item.isNotEmpty()) "0"+dec.format(item.toInt()).replace(",","-") else ""
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setVersion")
fun setVersion(tv: TextView, item: String?) {
    if(item == null){
        tv.text = "Ver. 1.0.0"
    }else{
        tv.text = "Ver. $item"
    }
}