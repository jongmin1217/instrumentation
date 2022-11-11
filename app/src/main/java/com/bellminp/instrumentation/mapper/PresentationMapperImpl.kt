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

fun DomainSites.mapToPresentation() : List<TreeData>? {
    return this.list?.map {
        TreeData(
            sitesList = SitesList(
                it.num,
                it.fieldNum,
                it.fieldName,
                it.name,
                it.managenum
            )
        )
    }
}

fun DomainSections.mapToPresentation() : List<TreeData>? {
    return this.list?.map {
        TreeData(
            sectionsList = SectionsList(
                it.num,
                it.siteNum,
                it.name,
                it.managenum
            )
        )
    }
}

fun DomainGauges.mapToPresentation() : List<TreeData>? {
    return this.list?.map {
        TreeData(
            gaugesList = GaugesList(
                it.type,
                it.num,
                it.sectionNum,
                it.name,
                it.managenum,
                it.vpos,
                it.position,
                it.measurepos,
                it.gaugetypeNum,
                it.chcount
            )
        )
    }
}

fun DomainGaugesGroup.mapToPresentation() : List<TreeData>? {
    return this.list?.map {
        TreeData(
            gaugesGroupList = GaugesGroupList(
                it.num,
                it.sectionNum,
                it.name,
                it.managenum,
                it.vpos,
                it.position,
                it.measurepos,
                it.gaugetypeNum,
                it.type
            )
        )
    }
}