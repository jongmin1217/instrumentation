package com.bellminp.domain.usecase

import com.bellminp.domain.model.DomainAllGauges
import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.repository.LocalRepository
import javax.inject.Inject

class LocalUseCase @Inject constructor(private val localRepository: LocalRepository) {

    fun getAutoLogin() : DomainAutoLogin{
        return localRepository.getAutoLogin()
    }

    fun setAutoLogin(autoLogin: DomainAutoLogin){
        localRepository.setAutoLogin(autoLogin)
    }

    fun setToken(token : String){
        localRepository.setToken(token)
    }

    fun getToken() : String{
        return localRepository.getToken()
    }

    fun setAdmin(admin: Boolean) {
        localRepository.setAdmin(admin)
    }

    fun getAdmin() = localRepository.getAdmin()

    fun getRotate() = localRepository.getRotate()

    fun setRotate(value: Boolean) {
        localRepository.setRotate(value)
    }

    fun getTreeSite() = localRepository.getTreeSite()

    fun setTreeSite(value: Boolean) {
        localRepository.setTreeSite(value)
    }

    fun getTreeSection() = localRepository.getTreeSection()

    fun setTreeSection(value: Boolean) {
        localRepository.setTreeSection(value)
    }

    fun getTreeGroup() = localRepository.getTreeGroup()

    fun setTreeGroup(value: Boolean) {
        localRepository.setTreeGroup(value)
    }

    fun getTreeGauges() = localRepository.getTreeGauges()

    fun setTreeGauges(value: Boolean) {
        localRepository.setTreeGauges(value)
    }

    fun getGraphDate() = localRepository.getGraphDate()

    fun setGraphDate(value: Int) {
        localRepository.setGraphDate(value)
    }

    fun getGraphPoint() = localRepository.getGraphPoint()

    fun setGraphPoint(value: Int) {
        localRepository.setGraphPoint(value)
    }

    fun initSetting() {
        localRepository.initSetting()
    }

    fun setAllGauges(allGauges : DomainAllGauges){
        localRepository.setDataAllGauges(allGauges)
    }

    fun getAllGauges(num : Int) = localRepository.getDataAllGauges(num)

    fun clear(){
        localRepository.clear()
    }
}