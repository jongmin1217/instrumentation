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

    fun setAllGauges(allGauges : DomainAllGauges){
        localRepository.setDataAllGauges(allGauges)
    }

    fun getAllGauges(num : Int) = localRepository.getDataAllGauges(num)

    fun clear(){
        localRepository.clear()
    }
}