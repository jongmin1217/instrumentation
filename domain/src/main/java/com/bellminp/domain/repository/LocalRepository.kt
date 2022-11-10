package com.bellminp.domain.repository

import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.model.DomainLogin

interface LocalRepository {
    fun setAutoLogin(autoLogin : DomainAutoLogin)

    fun getAutoLogin() : DomainAutoLogin

    fun setToken(token : String)

    fun getToken() : String

    fun clear()
}