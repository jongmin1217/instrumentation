package com.bellminp.domain.model

data class DomainGauges(
    val code : Int,
    val message : String,
    val list : List<DomainGaugesList>?
)

data class DomainGaugesList(
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