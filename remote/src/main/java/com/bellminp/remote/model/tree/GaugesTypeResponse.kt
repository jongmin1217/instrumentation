package com.bellminp.remote.model.tree

data class GaugesTypeResponse(
    val code : Int,
    val message : String,
    val list : List<GaugesTypeListResponse>?
)

data class GaugesTypeListResponse(
    val num : Int,
    val name : String,
    val chartType : Int,
    val outvaluecount : Int,
    val settingcount : Int
)