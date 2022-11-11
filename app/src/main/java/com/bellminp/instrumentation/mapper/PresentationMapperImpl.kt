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

fun DomainSites.mapToPresentation() : List<SitesList>? {
    return this.list?.map {
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

fun DomainSections.mapToPresentation() : List<SectionsList>? {
    return this.list?.map {
        SectionsList(
            it.num,
            it.siteNum,
            it.name,
            it.managenum,
            false
        )
    }
}

fun DomainGauges.mapToPresentation() : List<GaugesList>? {
    return this.list?.map {
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
            false
        )
    }
}

fun DomainGaugesGroup.mapToPresentation() : List<GaugesGroupList>? {
    return this.list?.map {
        GaugesGroupList(
            it.num,
            it.sectionNum,
            it.name,
            it.managenum,
            it.vpos,
            it.position,
            it.measurepos,
            it.gaugetypeNum,
            it.type,
            checked = false,
            clicked = false
        )
    }
}