package com.bellminp.domain.model

data class DomainGaugesGroup(
    val code : Int,
    val message : String,
    val list : List<DomainGaugesGroupList>?
)

data class DomainGaugesGroupList(
    val num : Int,
    val sectionNum : Int,
    val name : String,
    val managenum : String,
    val vpos : Double,
    val position : String,
    val measurepos : String,
    val gaugetypeNum : Int,
    val type : String
)