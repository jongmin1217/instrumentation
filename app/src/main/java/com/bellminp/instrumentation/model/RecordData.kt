package com.bellminp.instrumentation.model

data class RecordData(
    val time : Long,
    val msg : String,
    val gaugeNum : Int,
    val groupNum : Int,
    val sectionNum : Int,
    val fieldNum : Int,
    val fieldName : String,
    val gaugeName : String,
    val sectionName : String,
    val groupName : String?
)