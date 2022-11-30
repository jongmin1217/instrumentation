package com.bellminp.domain.model

data class DomainRecord(
    val code : Int,
    val message : String,
    val list : List<DomainRecordList>?
)

data class DomainRecordList(
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