package com.bellminp.domain.model

data class DomainGaugesGroupDetail(
    val code : Int,
    val message : String,
    val chartType : Int?,
    val list : List<DomainGaugesGroupDetailList>?,
    val chartList : List<DomainGaugesGroupDetailChart>?,
    val constantList : List<DomainGaugesGroupDetailConstantList>?
)

data class DomainGaugesGroupDetailList(
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

data class DomainGaugesGroupDetailChart(
    val time : Long,
    val list : List<DomainGaugesGroupDetailChartList>
)

data class DomainGaugesGroupDetailChartList(
    val gaugeNum : Int?,
    val vpos : Double?,
    val expM1 : Double?,
    val expM2 : Double?,
    val measurepos : String?,
    val x : Double?,
    val y : Double?
)

data class DomainGaugesGroupDetailConstantList(
    val num : Int,
    val gaugeNum : Int,
    val value : Double
)