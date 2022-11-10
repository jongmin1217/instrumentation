package com.bellminp.domain.model

data class DomainLogin(
    val code : Int,
    val message : String,
    val username : String?,
    val fieldNum : Int?,
    val authorityNum : Int?,
    val token : String?,
    val fieldList : List<DomainFieldList>?
)

data class DomainFieldList(
    val num : Int,
    val name : String
)