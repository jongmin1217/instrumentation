package com.bellminp.remote.model.detail

//data class GaugesDetailResponse(
//
//)

data class GaugesDetailListResponse(
    val chartType: Int,
    val datasettingName: String,
    val managenum: String,
    val gaugeNum: Int,
    val gaugeName: String,
    val groupNum: Int?,
    val groupName: String?,
    val reunit: String,
    val autorange: Boolean,
    val minrange: Double,
    val maxrange: Double,
    val ystep: Int,
    val vpos: Double,
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

data class MultiChartListResponse(
    val time: String,
    val firstValue: Double?,
    val secondValue: Double?,
    val thirdValue: Double?,
    val fourthValue: Double?,
    val fifthValue: Double?
)

//data class Chart5ListResponse(
//
//)


