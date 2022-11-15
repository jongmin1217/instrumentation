package com.bellminp.instrumentation.mapper

import com.bellminp.common.timberMsg
import com.bellminp.domain.model.*
import com.bellminp.instrumentation.model.*
import com.bellminp.instrumentation.utils.convertTimestampToDateRecord

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
fun List<DomainGaugesList>.mapToPresentation(): List<GaugesList> {
    return this.map {
        GaugesList(
            it.type,
            it.num,
            it.sectionNum,
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
fun List<DomainGaugesGroupList>.mapToPresentation(): List<GaugesGroupList> {
    return this.map {
        GaugesGroupList(
            it.num,
            it.sectionNum,
            it.gaugegroupNum,
            it.name,
            it.managenum,
            it.vpos,
            it.position,
            it.measurepos,
            it.gaugetypeNum,
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