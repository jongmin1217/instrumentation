package com.bellminp.data.mapper

import com.bellminp.data.model.*
import com.bellminp.domain.model.*

fun DomainAutoLogin.mapToData() : DataAutoLogin{
    return DataAutoLogin(this.username,this.password)
}

fun DataAutoLogin.mapToDomain() : DomainAutoLogin{
    return DomainAutoLogin(this.username,this.password)
}

fun DomainLogin.mapToData() : DataLogin{
    return DataLogin(
        this.code,
        this.message,
        this.username,
        this.fieldNum,
        this.authorityNum,
        this.token,
        this.fieldList?.map {
            DataFieldList(it.num,it.name)
        }
    )
}

fun DataLogin.mapToDomain() : DomainLogin{
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

fun DomainSites.mapToData(): DataSites {
    return DataSites(
        this.code,
        this.message,
        this.list?.map {
            DataSitesList(
                it.num,
                it.fieldNum,
                it.fieldName,
                it.name,
                it.managenum
            )
        }
    )
}

fun DomainSections.mapToData(): DataSections {
    return DataSections(
        this.code,
        this.message,
        this.list?.map {
            DataSectionsList(
                it.num,
                it.siteNum,
                it.name,
                it.managenum
            )
        }
    )
}

fun DomainGauges.mapToData(): DataGauges {
    return DataGauges(
        this.code,
        this.message,
        this.list?.map {
            DataGaugesList(
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
        }
    )
}

fun DomainGaugesGroup.mapToData(): DataGaugesGroup {
    return DataGaugesGroup(
        this.code,
        this.message,
        this.list?.map {
            DataGaugesGroupList(
                it.num,
                it.sectionNum,
                it.gaugegroupNum,
                it.name,
                it.managenum,
                it.vpos,
                it.position,
                it.measurepos,
                it.gaugetypeNum,
                it.type
            )
        }
    )
}

fun DomainGaugesType.mapToData(): DataGaugesType {
    return DataGaugesType(
        this.code,
        this.message,
        this.list?.map {
            DataGaugesTypeList(
                it.num,
                it.name,
                it.chartType,
                it.outvaluecount,
                it.settingcount
            )
        }
    )
}

fun DataSites.mapToDomain(): DomainSites {
    return DomainSites(
        this.code,
        this.message,
        this.list?.map {
            DomainSitesList(
                it.num,
                it.fieldNum,
                it.fieldName,
                it.name,
                it.managenum
            )
        }
    )
}

fun DataSections.mapToDomain(): DomainSections {
    return DomainSections(
        this.code,
        this.message,
        this.list?.map {
            DomainSectionsList(
                it.num,
                it.siteNum,
                it.name,
                it.managenum
            )
        }
    )
}

fun DataGauges.mapToDomain(): DomainGauges {
    return DomainGauges(
        this.code,
        this.message,
        this.list?.map {
            DomainGaugesList(
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
        }
    )
}

fun DataGaugesGroup.mapToDomain(): DomainGaugesGroup {
    return DomainGaugesGroup(
        this.code,
        this.message,
        this.list?.map {
            DomainGaugesGroupList(
                it.num,
                it.sectionNum,
                it.gaugegroupNum,
                it.name,
                it.managenum,
                it.vpos,
                it.position,
                it.measurepos,
                it.gaugetypeNum,
                it.type
            )
        }
    )
}

fun DataGaugesType.mapToDomain(): DomainGaugesType {
    return DomainGaugesType(
        this.code,
        this.message,
        this.list?.map {
            DomainGaugesTypeList(
                it.num,
                it.name,
                it.chartType,
                it.outvaluecount,
                it.settingcount
            )
        }
    )
}


fun DataRecord.mapToDomain(): DomainRecord {
    return DomainRecord(
        this.code,
        this.message,
        this.list?.map {
            DomainRecordList(
                it.time,
                it.msg,
                it.gaugeNum,
                it.groupNum,
                it.sectionNum,
                it.fieldNum,
                it.fieldName,
                it.gaugeName,
                it.sectionName,
                it.groupName
            )
        }
    )
}

fun DomainRecord.mapToData(): DataRecord {
    return DataRecord(
        this.code,
        this.message,
        this.list?.map {
            DataRecordList(
                it.time,
                it.msg,
                it.gaugeNum,
                it.groupNum,
                it.sectionNum,
                it.fieldNum,
                it.fieldName,
                it.gaugeName,
                it.sectionName,
                it.groupName
            )
        }
    )
}

