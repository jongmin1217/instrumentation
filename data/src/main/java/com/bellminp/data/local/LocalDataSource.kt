package com.bellminp.data.local

import com.bellminp.data.model.DataAutoLogin

interface LocalDataSource {

    fun setAutoLogin(autoLogin : DataAutoLogin)

    fun getAutoLogin() : DataAutoLogin

    fun setToken(token : String)

    fun getToken() : String

    fun setAdmin(admin : Boolean)

    fun getAdmin() : Boolean

    fun clear()
}