package com.bellminp.instrumentation.utils.bindingadapter

import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bellminp.instrumentation.InstrumentationApplication
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.mapper.mapToCellData
import com.bellminp.instrumentation.model.CellData
import com.bellminp.instrumentation.model.RecordData
import com.bellminp.instrumentation.ui.publicadapter.HorizontalAdapter

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