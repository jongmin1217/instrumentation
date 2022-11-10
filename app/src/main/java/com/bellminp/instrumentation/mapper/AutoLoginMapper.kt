package com.bellminp.instrumentation.mapper

import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.instrumentation.model.AutoLogin

object AutoLoginMapper : PresentationMapper<DomainAutoLogin,AutoLogin>{

    override fun mapToDomain(from: AutoLogin): DomainAutoLogin {
        return DomainAutoLogin(
            from.username,
            from.password
        )
    }

    override fun mapToPresentation(from: DomainAutoLogin): AutoLogin {
        return AutoLogin(
            from.username,
            from.password
        )
    }
}