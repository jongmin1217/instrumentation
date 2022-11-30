package com.bellminp.domain.model

data class DomainGaugesGroup(
    val code : Int,
    val message : String,
    val sectionNum : Int,
    val gaugegroupNum : Int,
    val gaugetypeNum : Int,
    val list : List<DomainGaugesGroupList>?
)

data class DomainGaugesGroupList(
    val num : Int,
    val name : String,
    val managenum : String,
    val vpos : Double,
    val measurepos : String,
    val type : String,
    val time : Long? = null
)