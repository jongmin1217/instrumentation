package com.bellminp.data

import com.bellminp.data.local.LocalDataSource
import com.bellminp.data.mapper.mapToData
import com.bellminp.data.mapper.mapToDomain
import com.bellminp.data.remote.RemoteDataSource
import com.bellminp.domain.model.DomainAllGauges
import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.model.DomainLogin
import com.bellminp.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl@Inject constructor(
    private val localDataSource: LocalDataSource
) : LocalRepository{
    override fun setAutoLogin(autoLogin: DomainAutoLogin) {
        localDataSource.setAutoLogin(autoLogin.mapToData())
    }

    override fun getAutoLogin(): DomainAutoLogin {
        return localDataSource.getAutoLogin().mapToDomain()
    }

    override fun setToken(token: String) {
        localDataSource.setToken(token)
    }

    override fun getToken(): String {
        return localDataSource.getToken()
    }

    override fun setAdmin(admin: Boolean) {
        localDataSource.setAdmin(admin)
    }

    override fun getAdmin() = localDataSource.getAdmin()

    override fun setDataAllGauges(dataAllGauges: DomainAllGauges) {
        localDataSource.setDataAllGauges(dataAllGauges.mapToData())
    }

    override fun getDataAllGauges(num : Int) = localDataSource.getDataAllGauges(num)

    override fun clear() {
        localDataSource.clear()
    }

}