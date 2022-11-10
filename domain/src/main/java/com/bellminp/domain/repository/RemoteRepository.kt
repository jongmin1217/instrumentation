package com.bellminp.domain.repository

import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.model.DomainLogin
import retrofit2.Response

interface RemoteRepository {

    suspend fun login(login : DomainAutoLogin) : Response<DomainLogin>
}