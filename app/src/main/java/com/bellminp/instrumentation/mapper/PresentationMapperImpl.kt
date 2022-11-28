package com.bellminp.instrumentation.mapper

import com.bellminp.common.timberMsg
import com.bellminp.data.model.*
import com.bellminp.domain.model.*
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.utils.convertTimestampToDateRecord
import com.bellminp.instrumentation.utils.getColorType
import com.bellminp.instrumentation.utils.graphLegendValue
import com.bellminp.instrumentation.utils.roundOff

fun DomainLogin.mapToPresentation(): Login {
    return Login(
        this.code,
        this.message,
        this.username,
        this.fieldNum,
        this.authorityNum,
        this.token,
        this.fieldList?.map {
            FieldList(it.num, it.name)
        }
    )
}

fun Login.mapToDomain(): DomainLogin {
    return DomainLogin(
        this.code,
        this.message,
        this.username,
        this.fieldNum,
        this.authorityNum,
        this.token,
        this.fieldList?.map {
            DomainFieldList(it.num, it.name)
        }
    )
}


fun DomainAutoLogin.mapToPresentation(): AutoLogin {
    return AutoLogin(
        this.username,
        this.password
    )
}

fun AutoLogin.mapToDomain(): DomainAutoLogin {
    return DomainAutoLogin(
        this.username,
        this.password
    )
}

fun List<DomainSitesList>.mapToPresentation(checked: Boolean = false): List<SitesList> {
    return this.map {
        SitesList(
            it.num,
            it.fieldNum,
            it.fieldName,
            it.name,
            it.managenum,
            checked
        )
    }
}

@JvmName("mapToPresentationDomainSectionsList")
fun List<DomainSectionsList>.mapToPresentation(checked: Boolean = false): List<SectionsList> {
    return this.map {
        SectionsList(
            it.num,
            it.siteNum,
            it.name,
            it.managenum,
            checked
        )
    }
}

@JvmName("mapToPresentationDomainGaugesList")
fun List<DomainGaugesList>.mapToPresentation(sectionsNum: Int): List<GaugesList> {
    return this.map {
        GaugesList(
            it.type,
            it.num,
            sectionsNum,
            it.name,
            it.managenum,
            it.vpos,
            it.position,
            it.measurepos,
            it.gaugetypeNum,
            it.chcount,
            checked = false,
            clicked = false,
            time = it.time
        )
    }
}

@JvmName("mapToPresentationDomainGaugesGroupList")
fun DomainGaugesGroup.mapToPresentation(): List<GaugesGroupList> {
    return this.list!!.map {
        GaugesGroupList(
            it.num,
            this.sectionNum,
            this.gaugegroupNum,
            it.name,
            it.managenum,
            it.vpos,
            it.measurepos,
            this.gaugetypeNum,
            it.type,
            clicked = false,
            time = it.time
        )
    }
}

@JvmName("mapToPresentationDomainRecordList")
fun List<DomainRecordList>.mapToPresentation(
    admin: Boolean
): List<RecordData> {
    val list = this.map {
        RecordData(
            it.time,
            it.msg,
            it.gaugeNum ?: 0,
            it.groupNum ?: 0,
            it.sectionNum ?: 0,
            it.fieldNum ?: 0,
            it.fieldName ?: "",
            it.gaugeName ?: "",
            it.sectionName ?: "",
            it.groupName,
            admin
        )
    }.toMutableList()

    val titleData = RecordData(
        0,
        "검사내용",
        0,
        0,
        0,
        0,
        "현장이름",
        "계측기명",
        "",
        "",
        admin,
        true
    )

    list.add(0, titleData)

    return list
}

fun List<RecordData>.mapToCellData(): List<RecordListData> {
    val list = ArrayList<RecordListData>()

    if (this.isNotEmpty()) {
        val timeItem = this.map {
            CellData(
                if (it.title) "검사일시" else graphLegendValue(it.time),
                it.title,
                cellTableData = if (it.title) null else CellTableData(it.time, it.gaugeNum)
            )
        }
        list.add(RecordListData(timeItem))

        val gaugesNameItem = this.map {
            CellData(
                it.gaugeName,
                it.title,
                cellTableData = if (it.title) null else CellTableData(it.time, it.gaugeNum)
            )
        }
        list.add(RecordListData(gaugesNameItem))

        val msgItem = this.map {
            CellData(
                it.msg,
                it.title,
                cellTableData = if (it.title) null else CellTableData(it.time, it.gaugeNum)
            )
        }
        list.add(RecordListData(msgItem))
    }

    return list
}

fun DomainGaugesDetail.mapToPresentation(): GaugesDetail {
    return GaugesDetail(
        this.code,
        this.message,
        this.chartType,
        this.multichart,
        this.list?.map {
            GaugesDetailList(
                it.chartType,
                it.datasettingName,
                it.managenum,
                it.gaugeNum,
                it.gaugeName,
                it.reunit,
                it.autorange,
                it.minrange,
                it.maxrange,
                it.ystep,
                it.hi1enable,
                it.hi2enable,
                it.hi3enable,
                it.low1enable,
                it.low2enable,
                it.low3enable,
                it.hi1,
                it.hi2,
                it.hi3,
                it.low1,
                it.low2,
                it.low3
            )
        },
        this.chartList?.map {
            GaugesDetailChartList(
                it.time,
                it.expM1,
                it.expM2,
                it.expM3,
                it.expM4,
                it.expT
            )
        }
    )
}

fun DomainGaugesGroupDetail.mapToPresentation(): GaugesGroupDetail {
    return GaugesGroupDetail(
        this.code,
        this.message,
        this.chartType,
        this.list?.map {
            GaugesGroupDetailList(
                it.chartType,
                it.managenum,
                it.groupNum,
                it.groupName,
                it.reunit,
                it.autorange,
                it.minrange,
                it.maxrange,
                it.ystep,
                it.hi1enable,
                it.hi2enable,
                it.hi3enable,
                it.low1enable,
                it.low2enable,
                it.low3enable,
                it.hi1,
                it.hi2,
                it.hi3,
                it.low1,
                it.low2,
                it.low3
            )
        },
        this.chartList?.map {
            GaugesGroupDetailChart(
                it.time,
                it.list.map { list ->
                    GaugesGroupDetailChartList(
                        list.expM1,
                        list.expM2,
                        list.x,
                        list.y
                    )
                }
            )
        },
        this.constantList?.map {
            GaugesGroupDetailConstantList(
                it.measurepos,
                it.x,
                it.y
            )
        },
        this.vposList
    )
}

fun GaugesDetail.dataToTableData(): List<TableData> {
    val list = ArrayList<TableData>()

    this.list?.let { gaugesDetailList ->
        val timeList = ArrayList<CellData>()
        timeList.add(CellData("측정일시", true))

        this.chartList?.let {
            it.forEach { chartList ->
                timeList.add(
                    CellData(
                        graphLegendValue(chartList.time),
                        false
                    )
                )
            }
        }

        list.add(TableData(timeList))

        for (i in gaugesDetailList.indices) {
            val gaugesList = ArrayList<CellData>()
            gaugesList.add(CellData(gaugesDetailList[i].reunit, true))

            this.chartList?.let { chartList ->
                chartList.forEach {
                    val value = when (i) {
                        0 -> roundOff(it.expM1 ?: 0.0)
                        1 -> roundOff(it.expM2 ?: 0.0)
                        2 -> roundOff(it.expM3 ?: 0.0)
                        3 -> roundOff(it.expM4 ?: 0.0)
                        else -> roundOff(it.expT ?: 0.0)
                    }

                    gaugesList.add(
                        CellData(
                            value.toString(), false,
                            getColorType(value, gaugesDetailList[i])
                        )
                    )
                }
            }

            list.add(TableData(gaugesList))
        }
    }

    return list
}

fun GaugesGroupDetail.dataToTableData(): List<TableData> {
    val list = ArrayList<TableData>()

    this.list?.let { gaugesDetailList ->
        val timeList = ArrayList<CellData>()
        timeList.add(CellData("측정일시", true))

        this.chartList?.let {
            it.forEach { chartList ->
                timeList.add(
                    CellData(
                        graphLegendValue(chartList.time),
                        false
                    )
                )
            }
        }

        list.add(TableData(timeList))

        val titleList = ArrayList<String>()


        this.chartList?.let { chartList ->
            chartList[0].list.size

            var index = 0
            chartList[0].list.forEach { detailChartList ->
                if (detailChartList.x != null) {
                    if (detailChartList.y != null) {
                        titleList.add("${this.constantList?.get(index)?.measurepos}(X)")
                        titleList.add("${this.constantList?.get(index)?.measurepos}(Y)")
                    } else {
                        titleList.add(this.constantList?.get(index)?.measurepos.toString())
                    }
                }

                if (detailChartList.expM1 != null) {
                    if (detailChartList.expM2 != null) {
                        titleList.add("${this.vposList?.get(index)}(m)")
                        titleList.add("${this.vposList?.get(index)}(m)")
                    } else {
                        titleList.add("${this.vposList?.get(index)}(m)")
                    }
                }
                index++
            }
        }

        for (i in titleList.indices) {

            val cellData = ArrayList<CellData>()
            cellData.add(CellData(titleList[i], true))
            this.chartList?.let {
                it.forEach { detailChart ->
                    val data = detailChart.list[i / gaugesDetailList.size]

                    if (gaugesDetailList.size == 2) {

                        if (i % 2 == 0) {
                            cellData.add(
                                CellData(
                                    roundOff(data.x ?: data.expM1 ?: 0.0).toString(), false,
                                    getColorType(data.x ?: data.expM1, gaugesDetailList[0])
                                )
                            )
                        } else {
                            cellData.add(
                                CellData(
                                    roundOff(data.y ?: data.expM2 ?: 0.0).toString(),
                                    false,
                                    getColorType(data.y ?: data.expM2, gaugesDetailList[1]),
                                    data.expM2 != null
                                )
                            )
                        }
                    } else {
                        cellData.add(
                            CellData(
                                roundOff(data.x ?: data.expM1 ?: 0.0).toString(), false,
                                getColorType(data.x ?: data.expM1, gaugesDetailList[0])
                            )
                        )
                    }
                }
            }
            list.add(TableData(cellData))
        }
    }

    val expM2List = list.filter { it.list[1].expM2 }
    if (expM2List.isNotEmpty()) {
        list.removeAll(expM2List.toSet())
        list.addAll(expM2List)
    }

    return list
}

fun GaugesDetail.dataToGraph(): List<GraphData> {
    val list = ArrayList<GraphType1>()

    val size = this.list?.size ?: 0
    val items = ArrayList<GraphGroupPointType1>()

    if (this.multichart == true) {
        val data = this.list?.get(0)

        for (i in 0 until size) {
            val groupItems = ArrayList<GraphPointType1>()

            this.chartList?.sortedBy { it.time }?.forEach {
                groupItems.add(
                    GraphPointType1(
                        it.time,
                        when (i) {
                            0 -> it.expM1
                            1 -> it.expM2
                            2 -> it.expM3
                            3 -> it.expM4
                            else -> it.expT
                        }
                    )
                )
            }


            items.add(GraphGroupPointType1(this.list?.get(i)?.reunit ?: "", groupItems))
        }



        data?.let {
            list.add(
                GraphType1(
                    it.managenum,
                    this.list?.get(0)?.reunit ?: "",
                    it.hi1enable,
                    it.hi2enable,
                    it.hi3enable,
                    it.low1enable,
                    it.low2enable,
                    it.low3enable,
                    it.hi1,
                    it.hi2,
                    it.hi3,
                    it.low1,
                    it.low2,
                    it.low3,
                    it.ystep,
                    it.reunit,
                    it.autorange,
                    it.minrange,
                    it.maxrange,
                    items
                )
            )
        }

    } else {
        for (i in 0 until size) {
            this.list?.get(i)?.let {
                val multiItems = ArrayList<GraphPointType1>()
                val multiSize = this.chartList?.size ?: 0

                for (j in 0 until multiSize) {
                    this.chartList?.sortedBy { chart -> chart.time }?.get(j)?.let { multiData ->
                        multiItems.add(
                            GraphPointType1(
                                multiData.time,
                                when (i) {
                                    0 -> multiData.expM1
                                    1 -> multiData.expM2
                                    2 -> multiData.expM3
                                    3 -> multiData.expM4
                                    else -> multiData.expT
                                }
                            )
                        )
                    }

                }

                list.add(
                    GraphType1(
                        it.managenum,
                        it.reunit,
                        it.hi1enable,
                        it.hi2enable,
                        it.hi3enable,
                        it.low1enable,
                        it.low2enable,
                        it.low3enable,
                        it.hi1,
                        it.hi2,
                        it.hi3,
                        it.low1,
                        it.low2,
                        it.low3,
                        it.ystep,
                        it.reunit,
                        it.autorange,
                        it.minrange,
                        it.maxrange,
                        listOf(GraphGroupPointType1(it.reunit, multiItems))
                    )
                )
            }
        }
    }

    return list
}

fun GaugesGroupDetail.dataToGraph(): List<GraphData> {
    val list = ArrayList<GraphType2>()

    val size = if (this.chartList?.get(0)?.list?.get(0)?.expM2 == null) 1 else 2

    for (i in 0 until size) {
        this.list?.get(i)?.let {
            val itemsList = ArrayList<GraphGroupPointType2>()

            this.chartList?.forEach { chartList ->
                val itemList = ArrayList<GraphPointType2>()

                for (j in chartList.list.indices) {
                    itemList.add(
                        GraphPointType2(
                            this.vposList?.get(j) ?: 0.0,
                            if (i == 0) chartList.list[j].expM1 else chartList.list[j].expM2
                        )
                    )
                }

                itemsList.add(GraphGroupPointType2(chartList.time, itemList))
            }

            list.add(
                GraphType2(
                    it.managenum,
                    "간격변위(mm)",
                    it.hi1enable,
                    it.hi2enable,
                    it.hi3enable,
                    it.low1enable,
                    it.low2enable,
                    it.low3enable,
                    it.hi1,
                    it.hi2,
                    it.hi3,
                    it.low1,
                    it.low2,
                    it.low3,
                    it.ystep,
                    it.reunit,
                    it.autorange,
                    it.minrange,
                    it.maxrange,
                    itemsList
                )
            )
        }
    }

    return list
}

fun GaugesGroupDetail.dataToGraph3(): List<GraphData> {
    val list = ArrayList<GraphType3>()

    val size = if (this.chartList?.get(0)?.list?.get(0)?.expM2 == null) 1 else 2

    for (i in 0 until size) {
        this.list?.get(i)?.let {
            val itemsList = ArrayList<GraphGroupPointType3>()

            this.chartList?.forEach { chartList ->
                val itemList = ArrayList<GraphPointType3>()

                for (j in chartList.list.indices) {
                    itemList.add(
                        GraphPointType3(
                            this.vposList?.get(j) ?: 0.0,
                            if (i == 0) chartList.list[j].expM1 else chartList.list[j].expM2
                        )
                    )
                }

                itemsList.add(GraphGroupPointType3(chartList.time, itemList))
            }

            list.add(
                GraphType3(
                    "누적간격변위",
                    "심도",
                    it.hi1enable,
                    it.hi2enable,
                    it.hi3enable,
                    it.low1enable,
                    it.low2enable,
                    it.low3enable,
                    it.hi1,
                    it.hi2,
                    it.hi3,
                    it.low1,
                    it.low2,
                    it.low3,
                    it.ystep,
                    it.reunit,
                    it.autorange,
                    it.minrange,
                    it.maxrange,
                    itemsList
                )
            )
        }
    }

    return list
}

fun GaugesGroupDetail.dataToGraph4(): List<GraphData> {
    val graphList = ArrayList<GraphType4>()

    val itemsList = ArrayList<GraphGroupPointType4>()

    this.chartList?.let {
        for (i in it.indices) {
            val itemList = ArrayList<GraphPointType4>()

            for (j in it[i].list.indices) {
                val standardX = this.constantList?.get(j)?.x ?: 0.0
                val standardY = this.constantList?.get(j)?.y ?: 0.0
                val x = (it[i].list[j].x ?: 0.0) * 150
                val y = (it[i].list[j].y ?: 0.0) * 150
                itemList.add(GraphPointType4(standardX + x, standardY + y,if(i == it.size -1) (it[i].list[j].x ?: 0.0) else 0.0,if(i == it.size -1) (it[i].list[j].y ?: 0.0) else 0.0))
            }
            itemsList.add(GraphGroupPointType4(it[i].time, itemList))
        }
    }

//    val standardList = ArrayList<GraphGroupPointStandard>()
//    this.constantList?.let {
//        this.list?.let { list->
//            for(i in 0..6){
//                val standardItemList = ArrayList<GraphPointType4>()
//
//                for(j in -1 until it.size){
//                    var standardX: Double
//                    var standardY: Double
//                    try {
//                        standardX = it[j].x
//                        standardY = it[j].y
//                    }catch (e : IndexOutOfBoundsException){
//                        standardX = 0.0
//                        standardY = 0.0
//                    }
//
//                    var x = 0.0
//                    var y = 0.0
//                    try {
//                        when(i){
//                            0 -> {
//                                if(list[0].hi1enable && list[1].hi1enable){
//                                    x = 1.1
//                                    y = 1.1
//                                }
//                            }
//
//                            1 -> {
//                                if(list[0].hi2enable && list[1].hi2enable){
//                                    x = 1.2
//                                    y = 1.2
//                                }
//                            }
//
//                            2 -> {
//                                if(list[0].hi3enable && list[1].hi3enable){
//                                    x = 1.3
//                                    y = 1.3
//                                }
//                            }
//
//                            3 -> {
//                                if(list[0].low1enable && list[1].low1enable){
//                                    x = 0.9
//                                    y = 0.9
//                                }
//                            }
//
//                            4 -> {
//                                if(list[0].low2enable && list[1].low2enable){
//                                    x = 0.8
//                                    y = 0.8
//                                }
//                            }
//
//                            5 -> {
//                                if(list[0].low3enable && list[1].low3enable){
//                                    x = 0.7
//                                    y = 0.7
//                                }
//                            }
//                        }
//                    }catch (e :Exception){}
//
//
//                    standardItemList.add(GraphPointType4(standardX * x,standardY * y))
//
//
//                }
//
////                when(i){
////                    0 -> {
////                        standardItemList.add(0,GraphPointType4(-list[0].hi1*100,0.0))
////                        standardItemList.add(GraphPointType4(it[it.size-1].x + list[0].hi1*100,0.0))
////                    }
////                    1 -> {
////                        standardItemList.add(0,GraphPointType4(-list[0].hi2*100,0.0))
////                        standardItemList.add(GraphPointType4(it[it.size-1].x + list[0].hi2*100,0.0))
////                    }
////                    2 -> {
////                        standardItemList.add(0,GraphPointType4(-list[0].hi3*100,0.0))
////                        standardItemList.add(GraphPointType4(it[it.size-1].x + list[0].hi3*100,0.0))
////                    }
////                }
//
//                standardList.add(GraphGroupPointStandard(i,standardItemList))
//            }
//
//            graphList.add(
//                GraphType4(
//                    "",
//                    "",
//                    list[0].ystep,
//                    list[0].reunit,
//                    list[0].autorange,
//                    list[0].minrange,
//                    list[0].maxrange,
//                    itemsList,
//                    standardList
//                )
//            )
//
//        }
//    }

    this.list?.let {
        graphList.add(
            GraphType4(
                "",
                "",
                list[0].ystep,
                list[0].reunit,
                list[0].autorange,
                list[0].minrange,
                list[0].maxrange,
                itemsList,
                arrayListOf()
            )
        )
    }

    return graphList
}

fun GaugesDetail.dataToGraph5(): List<GraphType5> {
    val list = ArrayList<GraphType5>()

    this.list?.let {
        this.chartList?.let { chartList ->
            val itemsList = ArrayList<GraphGroupPointType5>()
            chartList.forEach { data ->
                val itemList = ArrayList<GraphPointType5>()
                for (i in 0..360) {
                    itemList.add(
                        GraphPointType5(
                            i,
                            if (data.expM1 ?: 0.0 == i.toDouble()) data.expM2?:1.0 else 0.0
                        )
                    )
                }
                itemsList.add(GraphGroupPointType5(data.time, itemList))
            }

            list.add(
                GraphType5(
                    "",
                    "",
                    it[0].ystep,
                    it[0].reunit,
                    itemsList
                )
            )
        }
    }

    return list
}

fun GaugesDetail.dataToGraph7(): List<GraphData> {
    val list = ArrayList<GraphData>()

    this.list?.let {
        this.chartList?.let { chartList ->
            val itemsList = ArrayList<GraphGroupPointType5>()
            chartList.forEach { data ->
                val itemList = ArrayList<GraphPointType5>()
                for (i in 0..360) {
                    itemList.add(
                        GraphPointType5(
                            i,
                            if (data.expM1 ?: 0.0 == i.toDouble()) data.expM2?:1.0 else 0.0
                        )
                    )
                }
                itemsList.add(GraphGroupPointType5(data.time, itemList))
            }

            list.add(
                GraphType5(
                    "",
                    "",
                    it[0].ystep,
                    it[0].reunit,
                    itemsList
                )
            )

            it[2].let {
                val multiItems = ArrayList<GraphPointType1>()
                val multiSize = this.chartList.size

                for (j in 0 until multiSize) {
                    this.chartList.sortedBy { chart -> chart.time }[j].let { multiData ->
                        multiItems.add(
                            GraphPointType1(
                                multiData.time,
                                multiData.expM3
                            )
                        )
                    }

                }

                list.add(
                    GraphType1(
                        it.managenum,
                        it.reunit,
                        it.hi1enable,
                        it.hi2enable,
                        it.hi3enable,
                        it.low1enable,
                        it.low2enable,
                        it.low3enable,
                        it.hi1,
                        it.hi2,
                        it.hi3,
                        it.low1,
                        it.low2,
                        it.low3,
                        it.ystep,
                        it.reunit,
                        it.autorange,
                        it.minrange,
                        it.maxrange,
                        listOf(GraphGroupPointType1(it.reunit, multiItems))
                    )
                )
            }
        }
    }

    return list
}