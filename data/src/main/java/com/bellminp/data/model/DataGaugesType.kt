package com.bellminp.data.model

data class DataGaugesType(
    val code : Int,
    val message : String,
    val list : List<DataGaugesTypeList>?
)

data class DataGaugesTypeList(
    val num : Int,
    val name : String,
    val chartType : Int,
    val outvaluecount : Int,
    val settingcount : Int
)