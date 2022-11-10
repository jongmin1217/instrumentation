package com.bellminp.instrumentation.mapper

import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.model.DomainFieldList
import com.bellminp.domain.model.DomainLogin
import com.bellminp.instrumentation.model.AutoLogin
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.model.Login

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