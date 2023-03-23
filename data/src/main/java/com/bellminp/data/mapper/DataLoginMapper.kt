package com.bellminp.data.mapper

import com.bellminp.data.model.DataAutoLogin
import com.bellminp.data.model.DataFieldList
import com.bellminp.data.model.DataLogin
import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.model.DomainFieldList
import com.bellminp.domain.model.DomainLogin

object DataLoginMapper : DataMapper<DataLogin, DomainLogin> {
    override fun mapToDomain(from: DataLogin): DomainLogin {
        return DomainLogin(
            from.code,
            from.message,
            from.username,
            from.userId,
            from.fieldNum,
            from.authorityNum,
            from.token,
            from.fieldList?.map {
                DomainFieldList(it.num,it.name)
            },
            from.apichk,
            from.mobilenum,
            from.recvsms,
            from.appid,
            from.nsmip,
            from.nsmadminid,
            from.nsmdbname,
            from.nsmadminpw,
            from.appversion,
            from.smson
        )
    }

    override fun mapToData(from: DomainLogin): DataLogin {
        return DataLogin(
            from.code,
            from.message,
            from.username,
            from.userId,
            from.fieldNum,
            from.authorityNum,
            from.token,
            from.fieldList?.map {
                DataFieldList(it.num,it.name)
            },
            from.apichk,
            from.mobilenum,
            from.recvsms,
            from.appid,
            from.nsmip,
            from.nsmadminid,
            from.nsmdbname,
            from.nsmadminpw,
            from.appversion,
            from.smson
        )
    }
}