package com.bellminp.data.local

import com.bellminp.data.model.DataAllGauges
import com.bellminp.data.model.DataAutoLogin
import com.bellminp.domain.model.DomainAllGauges

interface LocalDataSource {

    fun setAutoLogin(autoLogin : DataAutoLogin)

    fun getAutoLogin() : DataAutoLogin

    fun setToken(token : String)

    fun getToken() : String

    fun setAdmin(admin : Boolean)

    fun getAdmin() : Boolean

    fun setDataAllGauges(dataAllGauges : DataAllGauges)

    fun getDataAllGauges(num : Int) : String

    fun clear()
}