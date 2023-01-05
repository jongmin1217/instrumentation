package com.bellminp.instrumentation.model

import com.bellminp.domain.model.DomainFieldList

data class Login(
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
) : java.io.Serializable

data class FieldData(
    val data : List<FieldList>
): java.io.Serializable