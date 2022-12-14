package com.bellminp.instrumentation.mapper

import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.model.DomainFieldList
import com.bellminp.domain.model.DomainLogin
import com.bellminp.instrumentation.model.AutoLogin
import com.bellminp.instrumentation.model.FieldList
import com.bellminp.instrumentation.model.Login

object LoginMapper : PresentationMapper<DomainLogin, Login>{

    override fun mapToDomain(from: Login): DomainLogin {
        return DomainLogin(
            from.code,
            from.message,
            from.username,
            from.fieldNum,
            from.authorityNum,
            from.token,
            from.fieldList?.map {
                DomainFieldList(it.num,it.name)
            }
        )
    }

    override fun mapToPresentation(from: DomainLogin): Login {
        return Login(
            from.code,
            from.message,
            from.username,
            from.fieldNum,
            from.authorityNum,
            from.token,
            from.fieldList?.map {
                FieldList(it.num,it.name)
            }
        )
    }
}