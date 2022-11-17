package com.bellminp.instrumentation.mapper

import com.bellminp.common.timberMsg
import com.bellminp.data.model.*
import com.bellminp.domain.model.*
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.utils.convertTimestampToDateRecord
import com.bellminp.instrumentation.utils.getColorType

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

fun List<DomainSitesList>.mapToPresentation(): List<SitesList> {
    return this.map {
        SitesList(
            it.num,
            it.fieldNum,
            it.fieldName,
            it.name,
            it.managenum,
            false
        )
    }
}

@JvmName("mapToPresentationDomainSectionsList")
fun List<DomainSectionsList>.mapToPresentation(): List<SectionsList> {
    return this.map {
        SectionsList(
            it.num,
            it.siteNum,
            it.name,
            it.managenum,
            false
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
            clicked = false
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
            clicked = false
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
        "내용",
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
        if (this[0].admin) {
            val fieldItem = this.map { CellData(it.fieldName, it.title) }
            list.add(RecordListData(fieldItem))
        }
        val timeItem = this.map {
            CellData(
                if (it.title) "측정일시" else convertTimestampToDateRecord(it.time),
                it.title
            )
        }
        list.add(RecordListData(timeItem))

        val gaugesNameItem = this.map { CellData(it.gaugeName, it.title) }
        list.add(RecordListData(gaugesNameItem))

        val msgItem = this.map { CellData(it.msg, it.title) }
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
                        list.gaugeNum,
                        list.vpos,
                        list.expM1,
                        list.expM2,
                        list.measurepos,
                        list.x,
                        list.y
                    )
                }
            )
        },
        this.constantList?.map {
            GaugesGroupDetailConstantList(
                it.num,
                it.gaugeNum,
                it.value
            )
        }
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
                        convertTimestampToDateRecord(chartList.time),
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
                    val value = when(i){
                        0 -> it.expM1
                        1 -> it.expM2
                        2 -> it.expM3
                        3 -> it.expM4
                        else -> it.expT
                    }

                    gaugesList.add(
                        CellData(
                            value?.toString() ?: "", false,
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

fun GaugesGroupDetail.dataToTableData() : List<TableData> {
    val list = ArrayList<TableData>()

    this.list?.let { gaugesDetailList ->
        val timeList = ArrayList<CellData>()
        timeList.add(CellData("측정일시", true))

        this.chartList?.let {
            it.forEach { chartList ->
                timeList.add(
                    CellData(
                        convertTimestampToDateRecord(chartList.time),
                        false
                    )
                )
            }
        }

        list.add(TableData(timeList))

        val titleList = ArrayList<String>()


        this.chartList?.let { chartList ->
            chartList[0].list.forEach { detailChartList ->
                if (detailChartList.measurepos != null) {
                    if (detailChartList.y != null) {
                        titleList.add("${detailChartList.measurepos} - X")
                        titleList.add("${detailChartList.measurepos} - Y")
                    } else {
                        titleList.add(detailChartList.measurepos)
                    }
                }

                if (detailChartList.vpos != null) {
                    if (detailChartList.expM2 != null) {
                        titleList.add("${detailChartList.vpos} - expM1")
                        titleList.add("${detailChartList.vpos} - expM2")
                    } else {
                        titleList.add(detailChartList.vpos.toString())
                    }
                }
            }
        }

        for(i in titleList.indices){

            val cellData = ArrayList<CellData>()
            cellData.add(CellData(titleList[i],true))
            this.chartList?.let {
                it.forEach { detailChart->
                    val data = detailChart.list[i/gaugesDetailList.size]

                    if(gaugesDetailList.size==2){

                        if(i%2 == 0){
                            cellData.add(CellData(data.x?.toString()?:data.expM1?.toString()?:"",false,
                                getColorType(data.x?:data.expM1,gaugesDetailList[0])))
                        }else{
                            cellData.add(CellData(data.y?.toString()?:data.expM2?.toString()?:"",false,
                                getColorType(data.y?:data.expM2,gaugesDetailList[1])))
                        }
                    }else{
                        cellData.add(CellData(data.x?.toString()?:data.expM1?.toString()?:"",false,
                            getColorType(data.x?:data.expM1,gaugesDetailList[0])))
                    }

                }
            }

            list.add(TableData(cellData))
        }


    }

    return list
}