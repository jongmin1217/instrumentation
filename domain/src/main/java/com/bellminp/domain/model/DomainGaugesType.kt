package com.bellminp.domain.model

data class DomainGaugesType(
    val code : Int,
    val message : String,
    val list : List<DomainGaugesTypeList>?
)

data class DomainGaugesTypeList(
    val num : Int,
    val name : String,
    val chartType : Int,
    val outvaluecount : Int,
    val settingcount : Int
)