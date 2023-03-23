package com.bellminp.remote.api

import com.bellminp.data.model.DataAutoLogin
import com.bellminp.remote.model.BaseResponse
import com.bellminp.remote.model.detail.GaugesDetailResponse
import com.bellminp.remote.model.detail.GaugesGroupDetailResponse
import com.bellminp.remote.model.login.LoginResponse
import com.bellminp.remote.model.record.RecordResponse
import com.bellminp.remote.model.setting.SettingResponse
import com.bellminp.remote.model.tree.*
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*


interface Api {

    @POST("/user/login")
    suspend fun login(
        @Body dataAutoLogin: DataAutoLogin
    ): Response<LoginResponse>

    @GET("/sites")
    suspend fun getSites(
        @Header("Authorization") Authorization: String,
        @Query("fieldNum") fieldNum: Int
    ): Response<SitesResponse>

    @GET("/sections")
    suspend fun getSections(
        @Header("Authorization") Authorization: String,
        @Query("siteNum") siteNum: Int
    ): Response<SectionsResponse>

    @GET("/gauges")
    suspend fun getGauges(
        @Header("Authorization") Authorization: String,
        @Query("sectionNum") sectionNum: Int
    ): Response<GaugesResponse>

    @GET("/gauges/in-group")
    suspend fun getGaugesGroup(
        @Header("Authorization") Authorization: String,
        @Query("gaugegroupNum") gaugegroupNum: Int
    ): Response<GaugesGroupResponse>

    @GET("/gauges/types")
    suspend fun getGaugesType(
        @Header("Authorization") Authorization: String
    ): Response<GaugesTypeResponse>

    @GET("/processlog")
    suspend fun getProcessLog(
        @Header("Authorization") Authorization: String,
        @Query("fieldNum") fieldNum: Int?,
        @Query("startUnixTime") startUnixTime: Long,
        @Query("endUnixTime") endUnixTime: Long
    ): Response<RecordResponse>

    @GET("/gauges/detail/{id}")
    suspend fun getGaugesDetail(
        @Header("Authorization") Authorization: String,
        @Path("id") gaugeId: Int,
        @Query("startUnixTime") startUnixTime: Long,
        @Query("endUnixTime") endUnixTime: Long
    ): Response<GaugesDetailResponse>

    @GET("/gauges/group/detail/{id}")
    suspend fun getGaugesGroupDetail(
        @Header("Authorization") Authorization: String,
        @Path("id") gaugegroupId: Int,
        @Query("startUnixTime") startUnixTime: Long,
        @Query("endUnixTime") endUnixTime: Long
    ): Response<GaugesGroupDetailResponse>

    @GET("/fields/setting/{num}")
    suspend fun getSetting(
        @Header("Authorization") Authorization: String,
        @Path("num") num: Int
    ): Response<SettingResponse>

    @PATCH("/fields/sms/{num}")
    suspend fun setSetting(
        @Header("Authorization") Authorization: String,
        @Path("num") num: Int,
        @Query("tnfieldchkSMS") tnfieldchkSMS: Int
    ): Response<BaseResponse>
}