package com.bellminp.instrumentation.model

data class CellData(
    val realText : String,
    var title : Boolean = false,
    var colorType : Int = 0,
    val expM2 : Boolean = false,
    val cellTableData: CellTableData? = null
)

data class CellTableData(
    val time : Long,
    val num : Int
)