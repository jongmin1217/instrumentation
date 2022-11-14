package com.bellminp.remote.mapper

import com.bellminp.data.model.*
import com.bellminp.remote.model.login.LoginResponse
import com.bellminp.remote.model.record.RecordResponse
import com.bellminp.remote.model.tree.*
import retrofit2.Response
import retrofit2.Retrofit

fun LoginResponse.loginToData(): DataLogin {
    return DataLogin(
        this.code,
        this.message,
        this.username,
        this.fieldNum,
        this.authorityNum,
        this.token,
        this.fieldList?.map {
            DataFieldList(it.num, it.name)
        }
    )
}

fun SitesResponse.sitesToData(): DataSites {
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

fun SectionsResponse.sectionsToData(): DataSections {
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

fun GaugesResponse.gaugesToData(): DataGauges {
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

fun GaugesGroupResponse.gaugesGroupToData(): DataGaugesGroup {
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

fun GaugesTypeResponse.gaugesTypeToData(): DataGaugesType {
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

fun RecordResponse.recordToData(): DataRecord {
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