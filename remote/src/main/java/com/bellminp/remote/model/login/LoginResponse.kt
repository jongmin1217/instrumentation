package com.bellminp.remote.model.login


data class LoginResponse(
    val code : Int,
    val message : String,
    val username : String?,
    val fieldNum : Int?,
    val authorityNum : Int?,
    val token : String?,
    val fieldList : List<FieldList>?
)

data class FieldList(
    val num : Int,
    val name : String
)

