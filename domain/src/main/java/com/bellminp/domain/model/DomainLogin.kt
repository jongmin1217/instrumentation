package com.bellminp.domain.model

data class DomainLogin(
    val code : Int,
    val message : String,
    val username : String?,
    val userId : String?,
    val fieldNum : Int?,
    val authorityNum : Int?,
    val token : String?,
    val fieldList : List<DomainFieldList>?,
    val apichk : Int,
    val mobilenum : String,
    val recvsms : Int,
    val appid : Int,
    val nsmip : String,
    val nsmadminid : String,
    val nsmdbname : String,
    val nsmadminpw : String,
    val appversion : String?,
    val smson : Int
)

data class DomainFieldList(
    val num : Int,
    val name : String
)