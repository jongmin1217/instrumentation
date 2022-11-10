package com.bellminp.data.mapper

import com.bellminp.data.model.DataAutoLogin
import com.bellminp.domain.model.DomainAutoLogin

object DataAutoLoginMapper : DataMapper<DataAutoLogin,DomainAutoLogin> {

    override fun mapToData(from: DomainAutoLogin): DataAutoLogin {
        return DataAutoLogin(from.username,from.password)
    }

    override fun mapToDomain(from: DataAutoLogin): DomainAutoLogin {
        return DomainAutoLogin(from.username,from.password)
    }
}