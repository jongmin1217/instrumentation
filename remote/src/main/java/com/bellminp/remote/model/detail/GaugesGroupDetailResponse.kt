package com.bellminp.remote.model.detail

data class GaugesGroupDetailResponse(
    val code : Int,
    val message : String,
    val chartType : Int?,
    val list : List<GaugesGroupDetailListResponse>?,
    val chartList : List<GaugesGroupDetailChartResponse>?,
    val constantList : List<GaugesGroupDetailConstantListResponse>?,
    val vposList : List<Double>?
)

data class GaugesGroupDetailListResponse(
    val chartType: Int,
    val managenum: String,
    val groupNum: Int,
    val groupName: String,
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

data class GaugesGroupDetailChartResponse(
    val time : Long,
    val list : List<GaugesGroupDetailChartListResponse>
)

data class GaugesGroupDetailChartListResponse(
    val expM1 : Double?,
    val expM2 : Double?,
    val x : Double?,
    val y : Double?
)

data class GaugesGroupDetailConstantListResponse(
    val measurepos : String,
    val x : Double,
    val y : Double
)