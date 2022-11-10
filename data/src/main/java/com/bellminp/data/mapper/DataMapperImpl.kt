package com.bellminp.data.mapper

import com.bellminp.data.model.DataAutoLogin
import com.bellminp.data.model.DataFieldList
import com.bellminp.data.model.DataLogin
import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.model.DomainFieldList
import com.bellminp.domain.model.DomainLogin

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

