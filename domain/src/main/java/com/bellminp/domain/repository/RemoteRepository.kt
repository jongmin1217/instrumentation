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

    suspend fun getProcessLog(token : String, fieldNum: Int?, startUnixTime: Long, endUnixTime: Long): Response<DomainRecord>

    suspend fun getGaugesDetail(token : String, gaugeId: Int, startUnixTime: Long, endUnixTime: Long): Response<DomainGaugesDetail>

    suspend fun getGaugesGroupDetail(token : String, gaugegroupId: Int, startUnixTime: Long, endUnixTime: Long): Response<DomainGaugesGroupDetail>

    suspend fun getSetting(token : String,num : Int) : Response<DomainSetting>

    suspend fun setSetting(token : String,num : Int,tnfieldchkSMS:Int) : Response<Pair<Int,String>>
}