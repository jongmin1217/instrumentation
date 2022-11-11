package com.bellminp.data.remote

import com.bellminp.data.model.*
import retrofit2.Response

interface RemoteDataSource {

    suspend fun login(login : DataAutoLogin) : Response<DataLogin>

    suspend fun getSites(token : String,fieldNum: Int): Response<DataSites>

    suspend fun getSections(token : String,siteNum: Int): Response<DataSections>

    suspend fun getGauges(token : String,sectionNum: Int): Response<DataGauges>

    suspend fun getGaugesGroup(token : String,gaugegroupNum: Int): Response<DataGaugesGroup>

    suspend fun getGaugesType(token : String): Response<DataGaugesType>
}