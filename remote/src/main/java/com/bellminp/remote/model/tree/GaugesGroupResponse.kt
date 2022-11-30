package com.bellminp.remote.model.tree

data class GaugesGroupResponse(
    val code : Int,
    val message : String,
    val sectionNum : Int,
    val gaugegroupNum : Int,
    val gaugetypeNum : Int,
    val list : List<GaugesGroupListResponse>?
)

data class GaugesGroupListResponse(
    val num : Int,
    val name : String,
    val managenum : String,
    val vpos : Double,
    val measurepos : String,
    val type : String,
    val time : Long? = null
)