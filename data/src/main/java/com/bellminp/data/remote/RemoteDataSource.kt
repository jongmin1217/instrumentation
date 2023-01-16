package com.bellminp.data.remote

import com.bellminp.data.model.*
import retrofit2.Response
import retrofit2.http.Query

interface RemoteDataSource {

    suspend fun login(login : DataAutoLogin) : Response<DataLogin>

    suspend fun getSites(token : String,fieldNum: Int): Response<DataSites>

    suspend fun getSections(token : String,siteNum: Int): Response<DataSections>

    suspend fun getGauges(token : String,sectionNum: Int): Response<DataGauges>

    suspend fun getGaugesGroup(token : String,gaugegroupNum: Int): Response<DataGaugesGroup>

    suspend fun getGaugesType(token : String): Response<DataGaugesType>

    suspend fun getProcessLog(token : String, fieldNum: Int?, startUnixTime: Long, endUnixTime: Long): Response<DataRecord>

    suspend fun getGaugesDetail(token : String, gaugeId: Int, startUnixTime: Long, endUnixTime: Long): Response<DataGaugesDetail>

    suspend fun getGaugesGroupDetail(token : String, gaugegroupId: Int, startUnixTime: Long, endUnixTime: Long): Response<DataGaugesGroupDetail>

    suspend fun getSetting(token : String,num : Int) : Response<DataSetting>

    suspend fun setSetting(token : String,num : Int,tnfieldchkSMS:Int) : Response<Pair<Int,String>>
}