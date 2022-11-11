package com.bellminp.instrumentation.model

data class TreeData(
    val sitesList : SitesList? = null,
    val sectionsList: SectionsList? = null,
    val gaugesList: GaugesList? = null,
    val gaugesGroupList: GaugesGroupList? = null
)

data class SitesList(
    val num : Int,
    val fieldNum : Int,
    val fieldName : String,
    val name : String,
    val managenum : String
)

data class SectionsList(
    val num : Int,
    val siteNum : Int,
    val name : String,
    val managenum : String
)

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
    val chcount : Int?
)

data class GaugesGroupList(
    val num : Int,
    val sectionNum : Int,
    val name : String,
    val managenum : String,
    val vpos : Double,
    val position : String,
    val measurepos : String,
    val gaugetypeNum : Int,
    val type : String
)

data class GaugesTypeList(
    val num : Int,
    val name : String,
    val chartType : Int,
    val outvaluecount : Int,
    val settingcount : Int
)