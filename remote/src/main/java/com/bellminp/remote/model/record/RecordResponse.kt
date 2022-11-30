package com.bellminp.remote.model.record

data class RecordResponse(
    val code : Int,
    val message : String,
    val list : List<RecordListResponse>?
)

data class RecordListResponse(
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