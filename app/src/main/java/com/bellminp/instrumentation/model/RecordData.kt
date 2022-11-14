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
    val groupName : String?,
    val admin : Boolean,
    val title : Boolean = false,
    val maxData : MaxRecordData
)

data class MaxRecordData(
    val maxField : String?,
    val maxTime : String,
    val maxGauges : String,
    val maxMsg : String
)

data class CellData(
    val realText : String,
    val maxWidthText : String
)