package com.bellminp.data

import com.bellminp.data.local.LocalDataSource
import com.bellminp.data.mapper.mapToData
import com.bellminp.data.mapper.mapToDomain
import com.bellminp.data.model.DataSites
import com.bellminp.data.remote.RemoteDataSource
import com.bellminp.domain.model.*
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

    override suspend fun getSites(token : String,fieldNum: Int): Response<DomainSites> {
        val response = remoteDataSource.getSites(token,fieldNum)
        return if(response.isSuccessful){
            Response.success(response.code(),response.body()?.mapToDomain())
        }else{
            Response.error(response.code(), response.errorBody() ?: ResponseBody.create(null,"error"))
        }
    }

    override suspend fun getSections(token : String,siteNum: Int): Response<DomainSections> {
        val response = remoteDataSource.getSections(token,siteNum)
        return if(response.isSuccessful){
            Response.success(response.code(),response.body()?.mapToDomain())
        }else{
            Response.error(response.code(), response.errorBody() ?: ResponseBody.create(null,"error"))
        }
    }

    override suspend fun getGauges(token : String,sectionNum: Int): Response<DomainGauges> {
        val response = remoteDataSource.getGauges(token,sectionNum)
        return if(response.isSuccessful){
            Response.success(response.code(),response.body()?.mapToDomain())
        }else{
            Response.error(response.code(), response.errorBody() ?: ResponseBody.create(null,"error"))
        }
    }

    override suspend fun getGaugesGroup(token : String,gaugegroupNum: Int): Response<DomainGaugesGroup> {
        val response = remoteDataSource.getGaugesGroup(token,gaugegroupNum)
        return if(response.isSuccessful){
            Response.success(response.code(),response.body()?.mapToDomain())
        }else{
            Response.error(response.code(), response.errorBody() ?: ResponseBody.create(null,"error"))
        }
    }

    override suspend fun getGaugesType(token : String): Response<DomainGaugesType> {
        val response = remoteDataSource.getGaugesType(token)
        return if(response.isSuccessful){
            Response.success(response.code(),response.body()?.mapToDomain())
        }else{
            Response.error(response.code(), response.errorBody() ?: ResponseBody.create(null,"error"))
        }
    }

}