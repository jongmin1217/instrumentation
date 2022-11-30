package com.bellminp.data.model

data class DataGaugesGroup(
    val code : Int,
    val message : String,
    val sectionNum : Int,
    val gaugegroupNum : Int,
    val gaugetypeNum : Int,
    val list : List<DataGaugesGroupList>?
)

data class DataGaugesGroupList(
    val num : Int,
    val name : String,
    val managenum : String,
    val vpos : Double,
    val measurepos : String,
    val type : String,
    val time : Long? = null
)