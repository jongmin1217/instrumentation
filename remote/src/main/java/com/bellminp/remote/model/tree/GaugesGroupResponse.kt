package com.bellminp.remote.model.tree

data class GaugesGroupResponse(
    val code : Int,
    val message : String,
    val list : List<GaugesGroupListResponse>?
)

data class GaugesGroupListResponse(
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