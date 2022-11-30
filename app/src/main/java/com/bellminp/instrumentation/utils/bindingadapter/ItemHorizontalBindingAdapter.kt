package com.bellminp.instrumentation.utils.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.model.CellData

@BindingAdapter("setTextColor")
fun setTextColor(tv: TextView, item: CellData) {
    val res = InstrumentationApplication.mInstance.resources
    if(item.title){
        tv.setTextColor(res.getColor(R.color.basic,null))
    }else{
        when(item.colorType){
            0 -> tv.setTextColor(res.getColor(R.color.basic,null))
            1 -> tv.setTextColor(res.getColor(R.color.blue,null))
            2 -> tv.setTextColor(res.getColor(R.color.orange,null))
            3 -> tv.setTextColor(res.getColor(R.color.red,null))
        }
    }

}