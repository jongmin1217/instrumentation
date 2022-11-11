package com.bellminp.domain.repository

import com.bellminp.domain.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteRepository {

    suspend fun login(login : DomainAutoLogin) : Response<DomainLogin>

    suspend fun getSites(token : String,fieldNum: Int): Response<DomainSites>

    suspend fun getSections(token : String,siteNum: Int): Response<DomainSections>

    suspend fun getGauges(token : String,sectionNum: Int): Response<DomainGauges>

    suspend fun getGaugesGroup(token : String,gaugegroupNum: Int): Response<DomainGaugesGroup>

    suspend fun getGaugesType(token : String): Response<DomainGaugesType>
}