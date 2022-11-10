package com.bellminp.domain.usecase

import com.bellminp.common.timberMsg
import com.bellminp.domain.model.ApiResult
import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.model.DomainLogin
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
}