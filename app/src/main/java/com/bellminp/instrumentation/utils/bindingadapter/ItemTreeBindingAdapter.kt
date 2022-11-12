package com.bellminp.instrumentation.utils.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.model.*

@BindingAdapter("setFieldName")
fun setFieldName(tv: TextView, item: Field) {
    tv.text = item.name
}

@BindingAdapter("setSitesName")
fun setSitesName(tv: TextView, item: SitesList) {
    val managenum = if(item.managenum.isNotEmpty()) "[${item.managenum}]" else String()
    val text = "${item.name} $managenum"
    tv.text = text
}

@BindingAdapter("setSectionsName")
fun setSectionsName(tv: TextView, item: SectionsList) {
    val managenum = if(item.managenum.isNotEmpty()) "[${item.managenum}]" else String()
    val text = "${item.name} $managenum"
    tv.text = text
}

@BindingAdapter("setGaugesName")
fun setGaugesName(tv: TextView, item: GaugesList) {
    val managenum = if(item.managenum.isNotEmpty()) "[${item.managenum}]" else String()
    val text = "${item.name} $managenum"
    tv.text = text
}

@BindingAdapter("setGaugesGroupName")
fun setGaugesGroupName(tv: TextView, item: GaugesGroupList) {
    val managenum = if(item.managenum.isNotEmpty()) "[${item.managenum}]" else String()
    val text = "${item.name} $managenum"
    tv.text = text
}

@BindingAdapter("setGaugesIconType","setGaugesIconGaugesType","setGaugesIconNum")
fun setGaugesIcon(iv: ImageView, type : String, gaugesType : Int,num : Int) {
    iv.setImageResource(
        if(type == "gauges"){
            when(gaugesType){
                1 -> R.drawable.icon_1
                2 -> R.drawable.icon_2
                3 -> R.drawable.icon_3
                4 -> R.drawable.icon_4
                5 -> R.drawable.icon_5
                6 -> R.drawable.icon_6
                7 -> R.drawable.icon_7
                8 -> R.drawable.icon_8
                9 -> R.drawable.icon_9
                10 -> R.drawable.icon_10
                11 -> R.drawable.icon_11
                12 -> R.drawable.icon_12
                13 -> R.drawable.icon_13
                14 -> R.drawable.icon_14
                15 -> R.drawable.icon_15
                16 -> R.drawable.icon_16
                17 -> R.drawable.icon_17
                18 -> R.drawable.icon_18
                19 -> R.drawable.icon_19
                20 -> R.drawable.icon_20
                21 -> R.drawable.icon_21
                22 -> R.drawable.icon_22
                23 -> R.drawable.icon_23
                24 -> R.drawable.icon_24
                25 -> R.drawable.icon_25
                26 -> R.drawable.icon_26
                27 -> R.drawable.icon_27
                28 -> R.drawable.icon_28
                33 -> R.drawable.icon_33
                34 -> R.drawable.icon_34
                35 -> R.drawable.icon_35
                36 -> R.drawable.icon_36
                37 -> R.drawable.icon_37
                38 -> R.drawable.icon_38
                39 -> R.drawable.icon_39
                40 -> R.drawable.icon_40
                41 -> R.drawable.icon_41
                42 -> R.drawable.icon_42
                43 -> R.drawable.icon_43
                44 -> R.drawable.icon_44
                45 -> R.drawable.icon_45
                46 -> R.drawable.icon_46
                47 -> R.drawable.icon_47
                48 -> R.drawable.icon_48
                49 -> R.drawable.icon_49
                50 -> R.drawable.icon_50
                51 -> R.drawable.icon_51
                52 -> R.drawable.icon_52
                53 -> R.drawable.icon_53
                54 -> R.drawable.icon_54
                55 -> R.drawable.icon_55
                56 -> R.drawable.icon_56
                57 -> R.drawable.icon_57
                58 -> R.drawable.icon_58
                59 -> R.drawable.icon_59
                60 -> R.drawable.icon_60
                61 -> R.drawable.icon_61
                62 -> R.drawable.icon_62
                else -> R.drawable.icon_29
            }
        }else{
            when(num){
                5 -> R.drawable.icon_5stm_inclinometergroup
                6 -> R.drawable.icon_6sta_inclinometergroup
                33 -> R.drawable.icon_33sta_railgroup1
                34 -> R.drawable.icon_34sta_hori2group1
                35 -> R.drawable.icon_35sta_inclinometergroup1
                36 -> R.drawable.icon_36stm_horiinclgroup1
                else -> R.drawable.icon_29sta_tunnelgroup1
            }
        }
    )
}