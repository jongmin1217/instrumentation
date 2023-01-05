package com.bellminp.instrumentation.model



data class Field(
    val num : Int,
    val name : String,
    var checked : Boolean
) : TreeModel{
    override fun getSectionsNum() = 0
    override fun getGaugesName() = ""
    override fun getGaugesNum() = 0
    override fun getGaugesType() = ""
    override fun getGroupNum() = num
    override fun noneItem() {}
    override fun getGaugesTime(): Long? = null

}

data class SitesList(
    val num : Int,
    val fieldNum : Int,
    val fieldName : String,
    val name : String,
    val managenum : String,
    var checked : Boolean,
    var bottomViewVisible : Boolean = true,
    var textGray : Boolean = false
) : TreeModel{
    override fun getSectionsNum() = 0
    override fun getGaugesName() = ""
    override fun getGaugesNum() = 0
    override fun getGaugesType() = ""
    override fun getGroupNum() = num
    override fun noneItem() {textGray = true}
    override fun getGaugesTime(): Long? = null
}

data class SectionsList(
    val num : Int,
    val siteNum : Int,
    val name : String,
    val managenum : String,
    var checked : Boolean,
    var bottomViewVisible : Boolean = true,
    var sitesLineVisible : Boolean = true,
    var textGray : Boolean = false
) : TreeModel{
    override fun getSectionsNum() = 0
    override fun getGaugesName() = ""
    override fun getGaugesNum() = 0
    override fun getGaugesType() = ""
    override fun getGroupNum() = num
    override fun noneItem() {textGray = true}
    override fun getGaugesTime(): Long? = null
}

data class GaugesList(
    val type : String,
    val num : Int,
    var sectionNum : Int,
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
    var siteNum : Int = 0,
    var textGray : Boolean = false,
    val time : Long? = null
) : TreeModel{
    fun getGroup() = type == "group"
    override fun getSectionsNum() = sectionNum
    override fun getGaugesName() : String {
        val managenumText = if(managenum.isEmpty()) "" else "[$managenum]"
        return "$name $managenumText"
    }
    override fun getGaugesNum() = num
    override fun getGaugesType() = type
    override fun getGroupNum() = num
    override fun noneItem() {textGray = true}
    override fun getGaugesTime() = time
}

data class GaugesGroupList(
    val num : Int,
    var sectionNum : Int,
    val gaugegroupNum : Int,
    val name : String,
    val managenum : String,
    val vpos : Double,
    val measurepos : String,
    val gaugetypeNum : Int,
    val type : String,
    var clicked : Boolean,
    var siteNum : Int = 0,
    var bottomViewVisible : Boolean = true,
    var sitesLineVisible : Boolean = true,
    var sectionsLineVisible : Boolean = true,
    var gaugesLineVisible : Boolean = true,
    val time : Long? = null
) : TreeModel{
    override fun getSectionsNum() = sectionNum
    override fun getGaugesName() : String {
        val managenumText = if(managenum.isEmpty()) "" else "[$managenum]"
        return "$name $managenumText"
    }
    override fun getGaugesNum() = num
    override fun getGaugesType() = type
    override fun getGroupNum() = gaugegroupNum
    override fun noneItem() {}
    override fun getGaugesTime() = time
}

data class GaugesTypeList(
    val num : Int,
    val name : String,
    val chartType : Int,
    val outvaluecount : Int,
    val settingcount : Int
)

interface TreeModel{
    fun getSectionsNum() : Int
    fun getGaugesName() : String
    fun getGaugesNum() : Int
    fun getGaugesType() : String
    fun getGroupNum() : Int
    fun noneItem()
    fun getGaugesTime() : Long?
}