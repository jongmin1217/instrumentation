package com.bellminp.instrumentation.model



data class Field(
    val num : Int,
    val name : String,
    var checked : Boolean
) : TreeModel

data class SitesList(
    val num : Int,
    val fieldNum : Int,
    val fieldName : String,
    val name : String,
    val managenum : String,
    var checked : Boolean
) : TreeModel

data class SectionsList(
    val num : Int,
    val siteNum : Int,
    val name : String,
    val managenum : String,
    var checked : Boolean
) : TreeModel

data class GaugesList(
    val type : String,
    val num : Int,
    val sectionNum : Int,
    val name : String,
    val managenum : String,
    val vpos : Double?,
    val position : String?,
    val measurepos : String?,
    val gaugetypeNum : Int?,
    val chcount : Int?,
    var checked : Boolean
) : TreeModel

data class GaugesGroupList(
    val num : Int,
    val sectionNum : Int,
    val name : String,
    val managenum : String,
    val vpos : Double,
    val position : String,
    val measurepos : String,
    val gaugetypeNum : Int,
    val type : String,
    var checked : Boolean,
    var clicked : Boolean
) : TreeModel

data class GaugesTypeList(
    val num : Int,
    val name : String,
    val chartType : Int,
    val outvaluecount : Int,
    val settingcount : Int
)

interface TreeModel