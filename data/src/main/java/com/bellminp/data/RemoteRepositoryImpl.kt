package com.bellminp.data

import com.bellminp.data.local.LocalDataSource
import com.bellminp.data.mapper.mapToData
import com.bellminp.data.mapper.mapToDomain
import com.bellminp.data.remote.RemoteDataSource
import com.bellminp.domain.model.DomainAutoLogin
import com.bellminp.domain.model.DomainLogin
import com.bellminp.domain.repository.RemoteRepository
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {

    override suspend fun login(login: DomainAutoLogin): Response<DomainLogin> {
        val response = remoteDataSource.login(login.mapToData())
        return if(response.isSuccessful){
            Response.success(response.code(),response.body()?.mapToDomain())
        }else{
            Response.error(response.code(), response.errorBody() ?: ResponseBody.create(null,"error"))
        }
    }
}