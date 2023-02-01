package com.bellminp.instrumentation.model

import com.bellminp.domain.model.DomainFieldList
import java.text.DecimalFormat

data class Login(
    val code : Int,
    val message : String,
    val username : String?,
    val userId : String?,
    val fieldNum : Int?,
    val authorityNum : Int?,
    val token : String?,
    val fieldList : List<FieldList>?,
    val apichk : Int,
    val mobilenum : String,
    val recvsms : Int,
    val appid : Int,
    val nsmip : String,
    val nsmadminid : String,
    val nsmdbname : String,
    val nsmadminpw : String,
    val appversion : String?,
    val smson: Int
)

data class Connect(
    val apichk : Int,
    val mobilenum : String,
    val recvsms : Int,
    val appid : Int,
    val nsmip : String,
    val nsmadminid : String,
    val nsmdbname : String,
    val nsmadminpw : String,
    val appversion : String?,
    val authorityNum : Int?,
    val username: String,
    val userId: String,
    val smson : Int
) : java.io.Serializable{
    fun getPermission() = when(authorityNum){
        1 -> "총괄관리자"
        2 -> "현장관리자"
        3 -> "일반사용자"
        else -> ""
    }

}

data class FieldList(
    val num : Int,
    val name : String
) : java.io.Serializable

data class FieldData(
    val data : List<FieldList>
): java.io.Serializable