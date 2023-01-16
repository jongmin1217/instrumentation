package com.bellminp.remote.model.login


data class LoginResponse(
    val code : Int,
    val message : String,
    val username : String?,
    val fieldNum : Int?,
    val authorityNum : Int?,
    val token : String?,
    val fieldList : List<FieldList>?,
    val apichk : Int,
    val mobilenum : String,
    val recvsms : Int,
    val connect : ConnectData
)

data class ConnectData(
    val appid : Int,
    val nsmip : String,
    val nsmadminid : String,
    val nsmdbname : String,
    val nsmadminpw : String,
    val appversion : String?
)

data class FieldList(
    val num : Int,
    val name : String
)

