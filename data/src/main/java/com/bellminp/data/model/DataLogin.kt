package com.bellminp.data.model


data class DataLogin(
    val code : Int,
    val message : String,
    val username : String?,
    val fieldNum : Int?,
    val authorityNum : Int?,
    val token : String?,
    val fieldList : List<DataFieldList>?
)

data class DataFieldList(
    val num : Int,
    val name : String
)

