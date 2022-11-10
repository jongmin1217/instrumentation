package com.bellminp.remote.mapper

import com.bellminp.data.model.DataFieldList
import com.bellminp.data.model.DataLogin
import com.bellminp.remote.model.login.FieldList
import com.bellminp.remote.model.login.LoginResponse

object RemoteLoginMapper : RemoteMapper<LoginResponse,DataLogin> {

    override fun mapToData(from: LoginResponse): DataLogin {
        return DataLogin(
            from.code,
            from.message,
            from.username,
            from.fieldNum,
            from.authorityNum,
            from.token,
            from.fieldList?.map {
                DataFieldList(it.num,it.name)
            }
        )
    }

    override fun mapToRemote(from: DataLogin): LoginResponse {
        return LoginResponse(
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