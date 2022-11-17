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
fun List<DomainGaugesList>.mapToPresentation(sectionsNum : Int): List<GaugesList> {
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
    admin: Boolean,
    maxData: MaxRecordData
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
            admin,
            maxData = maxData
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
        true,
        maxData = maxData
    )

    list.add(0, titleData)

    return list
}

fun List<DomainRecordList>.maxText(admin: Boolean): MaxRecordData {
    var maxFieldIndex = 0
    var maxTimeIndex = 0
    var maxGaugesIndex = 0
    var maxMsgIndex = 0

    for (i in this.indices) {
        if (i != 0) {

            if (admin) {
                if (this[maxFieldIndex].fieldName?.length ?: 0 < this[i].fieldName?.length ?: 0) maxFieldIndex =
                    i
            }
            if (convertTimestampToDateRecord(this[maxTimeIndex].time).length < convertTimestampToDateRecord(
                    this[i].time
                ).length
            ) maxTimeIndex = i
            if (this[maxGaugesIndex].gaugeName?.length ?: 0 < this[i].gaugeName?.length ?: 0) maxGaugesIndex = i
            if (this[maxMsgIndex].msg.length < this[i].msg.length) {
                maxMsgIndex = i
            }
        }
    }

    return MaxRecordData(
        if (admin) this[maxFieldIndex].fieldName else null,
        convertTimestampToDateRecord(this[maxTimeIndex].time),
        this[maxGaugesIndex].gaugeName ?: "",
        this[maxMsgIndex].msg
    )
}

fun RecordData.mapToCellData(maxRecordData: MaxRecordData): List<CellData> {
    val list = ArrayList<CellData>()

    if (this.admin) list.add(CellData(this.fieldName, maxRecordData.maxField ?: "",this.title))
    list.add(
        CellData(
            if (this.title) "측정일시" else convertTimestampToDateRecord(this.time),
            maxRecordData.maxTime,
            this.title
        )
    )
    list.add(CellData(this.gaugeName, maxRecordData.maxGauges,this.title))
    list.add(CellData(this.msg, maxRecordData.maxMsg,this.title))

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

fun DomainGaugesGroupDetail.mapToPresentation() : GaugesGroupDetail {
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
                        list.empM1,
                        list.empM2,
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

fun GaugesDetail.dataToTableData() : List<TableData>{
    val list = ArrayList<TableData>()

    val titleList = ArrayList<CellData>()
    titleList.add(CellData("측정일시","측정일시",true))
    this.list?.let { gaugesDetailList ->
        for(i in gaugesDetailList.indices){
            titleList.add(CellData(gaugesDetailList[i].reunit,gaugesDetailList[i].reunit,true))
        }
        list.add(TableData(titleList))

        this.chartList?.let {
            for(i in it.indices){
                val cellList = ArrayList<CellData>()
                cellList.add(CellData(convertTimestampToDateRecord(it[i].time),convertTimestampToDateRecord(it[i].time),false))

                if(list[i].list[0].maxWidthText.length < convertTimestampToDateRecord(it[i].time).length){
                    for(j in 0..i){
                        list[j].list[0].maxWidthText = convertTimestampToDateRecord(it[i].time)
                    }
                }

                val valueList = listOf(it[i].expM1,it[i].expM2,it[i].expM3,it[i].expM4,it[i].expT)

                for(j in 0 until titleList.size - 1){
                    cellList.add(CellData(valueList[j]?.toString()?:"",valueList[j]?.toString()?:"",false,
                        getColorType(valueList[j],gaugesDetailList[j])))

                    if(list[i].list[j+1].maxWidthText.length < valueList[j]?.toString()?.length?:0){
                        for(k in 0..i){
                            list[k].list[j+1].maxWidthText = valueList[j]?.toString()?:""
                        }
                    }
                }


                list.add(TableData(cellList))
            }
        }
    }

    return list
}