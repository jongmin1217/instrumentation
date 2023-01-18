package com.bellminp.remote.mapper

import com.bellminp.data.mapper.mapToData
import com.bellminp.data.model.*
import com.bellminp.remote.model.BaseResponse
import com.bellminp.remote.model.detail.GaugesDetailResponse
import com.bellminp.remote.model.detail.GaugesGroupDetailResponse
import com.bellminp.remote.model.login.LoginResponse
import com.bellminp.remote.model.record.RecordResponse
import com.bellminp.remote.model.setting.SettingResponse
import com.bellminp.remote.model.tree.*
import retrofit2.Response
import retrofit2.Retrofit

fun LoginResponse.loginToData(): DataLogin {
    return DataLogin(
        this.code,
        this.message,
        this.username,
        this.userId,
        this.fieldNum,
        this.authorityNum,
        this.token,
        this.fieldList?.map {
            DataFieldList(it.num, it.name)
        },
        this.apichk,
        this.mobilenum,
        this.recvsms,
        this.connect.appid,
        this.connect.nsmip,
        this.connect.nsmadminid,
        this.connect.nsmdbname,
        this.connect.nsmadminpw,
        this.connect.appversion
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
                it.chcount,
                it.time
            )
        }
    )
}

fun GaugesGroupResponse.gaugesGroupToData(): DataGaugesGroup {
    return DataGaugesGroup(
        this.code,
        this.message,
        this.sectionNum,
        this.gaugegroupNum,
        this.gaugetypeNum,
        this.list?.map {
            DataGaugesGroupList(
                it.num,
                it.name,
                it.managenum,
                it.vpos,
                it.measurepos,
                it.type,
                it.time
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

fun GaugesDetailResponse.gaugesDetailToData(): DataGaugesDetail {
    return DataGaugesDetail(
        this.code,
        this.message,
        this.chartType,
        this.multichart,
        this.list?.map {
            DataGaugesDetailList(
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
            DataGaugesDetailChartList(
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

fun GaugesGroupDetailResponse.gaugesGroupDetailToData() : DataGaugesGroupDetail{
    return DataGaugesGroupDetail(
        this.code,
        this.message,
        this.chartType,
        this.list?.map {
            DataGaugesGroupDetailList(
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
            DataGaugesGroupDetailChart(
                it.time,
                it.list.map { list ->
                    DataGaugesGroupDetailChartList(
                        list.expM1,
                        list.expM2,
                        list.x,
                        list.y
                    )
                }
            )
        },
        this.constantList?.map {
            DataGaugesGroupDetailConstantList(
                it.measurepos,
                it.x,
                it.y
            )
        },
        this.vposList
    )
}

fun SettingResponse.settingToData() : DataSetting{
    return DataSetting(
        this.code,
        this.message,
        this.tnfieldchkSMS,
        this.lorachk,
        this.apiDataYuji
    )
}

fun BaseResponse.baseToPair() : Pair<Int,String>{
    return this.code to this.message
}