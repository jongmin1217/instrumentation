package com.bellminp.data.model

data class DataGauges(
    val code : Int,
    val message : String,
    val list : List<DataGaugesList>?
)

data class DataGaugesList(
    val type : String,
    val num : Int,
    val sectionNum : Int,
    val name : String,
    val managenum : String,
    val vpos : Double?,
    val position : String?,
    val measurepos : String?,
    val gaugetypeNum : Int?,
    val chcount : Int?
)