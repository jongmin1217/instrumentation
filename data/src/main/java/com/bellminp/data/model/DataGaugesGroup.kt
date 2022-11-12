package com.bellminp.data.model

data class DataGaugesGroup(
    val code : Int,
    val message : String,
    val list : List<DataGaugesGroupList>?
)

data class DataGaugesGroupList(
    val num : Int,
    val sectionNum : Int,
    val gaugegroupNum : Int,
    val name : String,
    val managenum : String,
    val vpos : Double,
    val position : String,
    val measurepos : String,
    val gaugetypeNum : Int,
    val type : String
)