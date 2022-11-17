package com.bellminp.instrumentation.model

import com.bellminp.domain.model.DomainGaugesDetailChartList
import com.bellminp.domain.model.DomainGaugesDetailList
import com.bellminp.instrumentation.mapper.dataToTableData

data class GaugesDetail(
    val code : Int,
    val message : String,
    val chartType: Int?,
    val multichart : Boolean?,
    val list : List<GaugesDetailList>?,
    val chartList : List<GaugesDetailChartList>?
) : GaugesData{
    override fun getTableData() = this.dataToTableData()
}

data class GaugesDetailList(
    val chartType: Int,
    val datasettingName: String,
    val managenum: String,
    val gaugeNum: Int,
    val gaugeName: String,
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

data class GaugesDetailChartList(
    val time: Long,
    val expM1: Double?,
    val expM2: Double?,
    val expM3: Double?,
    val expM4: Double?,
    val expT: Double?
)

data class GaugesGroupDetail(
    val code : Int,
    val message : String,
    val chartType : Int?,
    val list : List<GaugesGroupDetailList>?,
    val chartList : List<GaugesGroupDetailChart>?,
    val constantList : List<GaugesGroupDetailConstantList>?
) : GaugesData{
    override fun getTableData() : List<TableData>{
        return ArrayList<TableData>()
    }
}

data class GaugesGroupDetailList(
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

data class GaugesGroupDetailChart(
    val time : Long,
    val list : List<GaugesGroupDetailChartList>
)

data class GaugesGroupDetailChartList(
    val gaugeNum : Int?,
    val vpos : Double?,
    val empM1 : Double?,
    val empM2 : Double?,
    val measurepos : String?,
    val x : Double?,
    val y : Double?
)

data class GaugesGroupDetailConstantList(
    val num : Int,
    val gaugeNum : Int,
    val value : Double
)

interface GaugesData{
    fun getTableData() : List<TableData>
}