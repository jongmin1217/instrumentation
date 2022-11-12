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
    var checked : Boolean,
    var bottomViewVisible : Boolean = true
) : TreeModel

data class SectionsList(
    val num : Int,
    val siteNum : Int,
    val name : String,
    val managenum : String,
    var checked : Boolean,
    var bottomViewVisible : Boolean = true,
    var sitesLineVisible : Boolean = true
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
    var checked : Boolean,
    var clicked : Boolean,
    var bottomViewVisible : Boolean = true,
    var sitesLineVisible : Boolean = true,
    var sectionsLineVisible : Boolean = true,
    var siteNum : Int = 0
) : TreeModel{
    fun getGroup() = type == "group"
}

data class GaugesGroupList(
    val num : Int,
    val sectionNum : Int,
    val gaugegroupNum : Int,
    val name : String,
    val managenum : String,
    val vpos : Double,
    val position : String,
    val measurepos : String,
    val gaugetypeNum : Int,
    val type : String,
    var clicked : Boolean,
    var siteNum : Int = 0,
    var bottomViewVisible : Boolean = true,
    var sitesLineVisible : Boolean = true,
    var sectionsLineVisible : Boolean = true,
    var gaugesLineVisible : Boolean = true
) : TreeModel

data class GaugesTypeList(
    val num : Int,
    val name : String,
    val chartType : Int,
    val outvaluecount : Int,
    val settingcount : Int
)

interface TreeModel