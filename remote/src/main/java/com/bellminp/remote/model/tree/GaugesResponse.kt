package com.bellminp.remote.model.tree

data class GaugesResponse(
    val code : Int,
    val message : String,
    val list : List<GaugesListResponse>?
)

data class GaugesListResponse(
    val type : String,
    val num : Int,
    val sectionNum : Int,
    val name : String,
    val managenum : String,
    val vpos : Double?,
    val position : String?,
    val measurepos : String?,
    val gaugetypeNum : Int?,
    val chcount : Int?,
    val time : Long? = null
)