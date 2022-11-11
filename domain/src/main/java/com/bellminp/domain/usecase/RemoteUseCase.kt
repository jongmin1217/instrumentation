package com.bellminp.domain.usecase

import com.bellminp.common.timberMsg
import com.bellminp.domain.model.*
import com.bellminp.domain.repository.BaseRepository
import com.bellminp.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteUseCase @Inject constructor(private val remoteRepository: RemoteRepository) : BaseRepository(){

    suspend fun login(login : DomainAutoLogin) : Flow<ApiResult<DomainLogin>>{
        return flow {
            val result = getResponse(remoteRepository.login(login))
            emit(result)
        }
    }

    suspend fun getSites(token : String,fieldNum : Int) : Flow<ApiResult<DomainSites>>{
        return flow {
            val result = getResponse(remoteRepository.getSites(token,fieldNum))
            emit(result)
        }
    }

    suspend fun getSections(token : String,siteNum : Int) : Flow<ApiResult<DomainSections>>{
        return flow {
            val result = getResponse(remoteRepository.getSections(token,siteNum))
            emit(result)
        }
    }

    suspend fun getGauges(token : String,sectionNum : Int) : Flow<ApiResult<DomainGauges>>{
        return flow {
            val result = getResponse(remoteRepository.getGauges(token,sectionNum))
            emit(result)
        }
    }

    suspend fun getGaugesGroup(token : String,gaugegroupNum : Int) : Flow<ApiResult<DomainGaugesGroup>>{
        return flow {
            val result = getResponse(remoteRepository.getGaugesGroup(token,gaugegroupNum))
            emit(result)
        }
    }

    suspend fun getGaugesType(token : String) : Flow<ApiResult<DomainGaugesType>>{
        return flow {
            val result = getResponse(remoteRepository.getGaugesType(token))
            emit(result)
        }
    }
}