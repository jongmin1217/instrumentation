package com.bellminp.instrumentation.model

data class CellData(
    val realText : String,
    var title : Boolean = false,
    var colorType : Int = 0,
    val expM2 : Boolean = false,
    val cellTableData: CellTableData? = null,
    val type : Int = 0,
    val time : Boolean = false
){
    fun getRecordInfo() = realText == "검사내용" && title
}

data class CellTableData(
    val time : Long,
    val num : Int
)