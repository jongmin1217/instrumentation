package com.bellminp.remote.mapper

import com.bellminp.data.model.DataFieldList
import com.bellminp.data.model.DataLogin
import com.bellminp.remote.model.login.LoginResponse
import retrofit2.Response
import retrofit2.Retrofit

fun LoginResponse.loginToData() : DataLogin{
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