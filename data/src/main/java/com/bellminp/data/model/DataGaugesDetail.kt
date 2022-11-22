package com.bellminp.data.model

data class DataGaugesDetail(
    val code : Int,
    val message : String,
    val chartType: Int?,
    val multichart : Boolean?,
    val list : List<DataGaugesDetailList>?,
    val chartList : List<DataGaugesDetailChartList>?
)

data class DataGaugesDetailList(
    val chartType: Int,
    val datasettingName: String,
    val managenum: String,
    val gaugeNum: Int,
    val gaugeName: String,
    val reunit: String,
    val autorange: Boolean,
    val minrange: Double,
    val maxrange: Double,
    val ystep: Double,
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

data class DataGaugesDetailChartList(
    val time: Long,
    val expM1: Double?,
    val expM2: Double?,
    val expM3: Double?,
    val expM4: Double?,
    val expT: Double?
)