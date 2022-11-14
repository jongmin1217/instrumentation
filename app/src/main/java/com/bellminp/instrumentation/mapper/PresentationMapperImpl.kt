package com.bellminp.instrumentation.mapper

import com.bellminp.domain.model.*
import com.bellminp.instrumentation.model.*

fun DomainLogin.mapToPresentation() : Login{
    return Login(
        this.code,
        this.message,
        this.username,
        this.fieldNum,
        this.authorityNum,
        this.token,
        this.fieldList?.map {
            FieldList(it.num,it.name)
        }
    )
}

fun Login.mapToDomain() : DomainLogin{
    return DomainLogin(
        this.code,
        this.message,
        this.username,
        this.fieldNum,
        this.authorityNum,
        this.token,
        this.fieldList?.map {
            DomainFieldList(it.num,it.name)
        }
    )
}


fun DomainAutoLogin.mapToPresentation() : AutoLogin{
    return AutoLogin(
        this.username,
        this.password
    )
}

fun AutoLogin.mapToDomain() : DomainAutoLogin{
    return DomainAutoLogin(
        this.username,
        this.password
    )
}

fun List<DomainSitesList>.mapToPresentation() : List<SitesList> {
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
fun List<DomainSectionsList>.mapToPresentation() : List<SectionsList> {
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
fun List<DomainGaugesList>.mapToPresentation() : List<GaugesList> {
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
fun List<DomainGaugesGroupList>.mapToPresentation() : List<GaugesGroupList> {
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
fun List<DomainRecordList>.mapToPresentation() : List<RecordData>{
    return this.map {
        RecordData(
            it.time,
            it.msg,
            it.gaugeNum?:0,
            it.groupNum?:0,
            it.sectionNum?:0,
            it.fieldNum?:0,
            it.fieldName?:"",
            it.gaugeName?:"",
            it.sectionName?:"",
            it.groupName
        )
    }
}