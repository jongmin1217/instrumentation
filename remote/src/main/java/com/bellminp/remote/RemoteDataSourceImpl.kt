package com.bellminp.remote

import com.bellminp.data.model.*
import com.bellminp.data.remote.RemoteDataSource
import com.bellminp.remote.api.Api
import com.bellminp.remote.mapper.*
import com.bellminp.remote.model.login.LoginResponse
import com.bellminp.remote.utils.CONNECT_NAME
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import javax.xml.parsers.FactoryConfigurationError

class RemoteDataSourceImpl @Inject constructor(
    private val api: Api
) : RemoteDataSource{

    companion object{
        const val SUCCESS = 0
        const val FAIL = 1
        const val ERROR = 2
    }

    override suspend fun login(login: DataAutoLogin): Response<DataLogin> {
        val response = api.login(login.apply { connectName = CONNECT_NAME })
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.loginToData())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(500, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    override suspend fun getSites(token : String,fieldNum: Int): Response<DataSites> {
        val response = api.getSites(token,fieldNum)
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.sitesToData())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(500, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    override suspend fun getSections(token : String,siteNum: Int): Response<DataSections> {
        val response = api.getSections(token,siteNum)
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.sectionsToData())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(500, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    override suspend fun getGauges(token : String,sectionNum: Int): Response<DataGauges> {
        val response = api.getGauges(token,sectionNum)
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.gaugesToData())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(500, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    override suspend fun getGaugesGroup(token : String,gaugegroupNum: Int): Response<DataGaugesGroup> {
        val response = api.getGaugesGroup(token,gaugegroupNum)
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.gaugesGroupToData())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(500, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    override suspend fun getGaugesType(token : String): Response<DataGaugesType> {
        val response = api.getGaugesType(token)
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.gaugesTypeToData())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(500, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    override suspend fun getProcessLog(
        token: String,
        fieldNum: Int?,
        startUnixTime: Long,
        endUnixTime: Long
    ): Response<DataRecord> {
        val response = api.getProcessLog(token,fieldNum,startUnixTime,endUnixTime)
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.recordToData())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(500, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    override suspend fun getGaugesDetail(
        token: String,
        gaugeId: Int,
        startUnixTime: Long,
        endUnixTime: Long
    ): Response<DataGaugesDetail> {
        val response = api.getGaugesDetail(token,gaugeId,startUnixTime,endUnixTime)
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.gaugesDetailToData())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(500, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    override suspend fun getGaugesGroupDetail(
        token: String,
        gaugegroupId: Int,
        startUnixTime: Long,
        endUnixTime: Long
    ): Response<DataGaugesGroupDetail> {
        val response = api.getGaugesGroupDetail(token,gaugegroupId,startUnixTime,endUnixTime)
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.gaugesGroupDetailToData())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(200, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    override suspend fun getSetting(token : String,num: Int): Response<DataSetting> {
        val response = api.getSetting(token,num)
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.settingToData())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(200, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    override suspend fun setSetting(token : String,num: Int,tnfieldchkSMS:Int): Response<Pair<Int, String>> {
        val response = api.setSetting(token,num,tnfieldchkSMS)
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.baseToPair())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(200, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    private fun <T> getResponse(response: Response<T>): Int {
        return try {
            if (response.isSuccessful) SUCCESS
            else FAIL
        } catch (e: Exception) {
            ERROR
        }
    }
}