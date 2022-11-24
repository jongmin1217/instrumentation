package com.bellminp.domain.repository

import com.bellminp.domain.model.DomainAllGauges
import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.model.DomainLogin

interface LocalRepository {
    fun setAutoLogin(autoLogin : DomainAutoLogin)

    fun getAutoLogin() : DomainAutoLogin

    fun setToken(token : String)

    fun getToken() : String

    fun setAdmin(admin : Boolean)

    fun getAdmin() : Boolean

    fun setDataAllGauges(dataAllGauges : DomainAllGauges)

    fun getDataAllGauges(num : Int) : String

    fun clear()
}