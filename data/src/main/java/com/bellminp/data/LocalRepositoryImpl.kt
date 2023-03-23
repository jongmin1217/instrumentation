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

    override fun getRotate() = localDataSource.getRotate()

    override fun setRotate(value: Boolean) {
        localDataSource.setRotate(value)
    }

    override fun getTreeSite() = localDataSource.getTreeSite()

    override fun setTreeSite(value: Boolean) {
        localDataSource.setTreeSite(value)
    }

    override fun getTreeSection() = localDataSource.getTreeSection()

    override fun setTreeSection(value: Boolean) {
        localDataSource.setTreeSection(value)
    }

    override fun getTreeGroup() = localDataSource.getTreeGroup()

    override fun setTreeGroup(value: Boolean) {
        localDataSource.setTreeGroup(value)
    }

    override fun getTreeGauges() = localDataSource.getTreeGauges()

    override fun setTreeGauges(value: Boolean) {
        localDataSource.setTreeGauges(value)
    }

    override fun getGraphDate() = localDataSource.getGraphDate()

    override fun setGraphDate(value: Int) {
        localDataSource.setGraphDate(value)
    }

    override fun getGraphPoint() = localDataSource.getGraphPoint()

    override fun setGraphPoint(value: Int) {
        localDataSource.setGraphPoint(value)
    }

    override fun initSetting() {
        localDataSource.initSetting()
    }

    override fun setDataAllGauges(dataAllGauges: DomainAllGauges) {
        localDataSource.setDataAllGauges(dataAllGauges.mapToData())
    }

    override fun getDataAllGauges(num : Int) = localDataSource.getDataAllGauges(num)

    override fun clear() {
        localDataSource.clear()
    }

}