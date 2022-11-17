package com.bellminp.data.model

data class DataGaugesGroupDetail(
    val code : Int,
    val message : String,
    val chartType : Int?,
    val list : List<DataGaugesGroupDetailList>?,
    val chartList : List<DataGaugesGroupDetailChart>?,
    val constantList : List<DataGaugesGroupDetailConstantList>?
)

data class DataGaugesGroupDetailList(
    val chartType: Int,
    val managenum: String,
    val groupNum: Int,
    val groupName: String,
    val reunit: String,
    val autorange: Boolean,
    val minrange: Double,
    val maxrange: Double,
    val ystep: Int,
    val hi1enable: Boolean,
    val hi2enable: Boolean,
    val hi3enable: Boolean,
    val low1enable: Boolean,
    val low2enable: Boolean,
    val low3enable: Boolean,
    val hi1: Double,
    val hi2: Double,
    val hi3: Double,
    val low1: Double,
    val low2: Double,
    val low3: Double
)

data class DataGaugesGroupDetailChart(
    val time : Long,
    val list : List<DataGaugesGroupDetailChartList>
)

data class DataGaugesGroupDetailChartList(
    val gaugeNum : Int?,
    val vpos : Double?,
    val empM1 : Double?,
    val empM2 : Double?,
    val measurepos : String?,
    val x : Double?,
    val y : Double?
)

data class DataGaugesGroupDetailConstantList(
    val num : Int,
    val gaugeNum : Int,
    val value : Double
)