package com.bellminp.data.model

data class DataRecord(
    val code : Int,
    val message : String,
    val list : List<DataRecordList>?
)

data class DataRecordList(
    val time : Long,
    val msg : String,
    val gaugeNum : Int?,
    val groupNum : Int?,
    val sectionNum : Int?,
    val fieldNum : Int?,
    val fieldName : String?,
    val gaugeName : String?,
    val sectionName : String?,
    val groupName : String?
)