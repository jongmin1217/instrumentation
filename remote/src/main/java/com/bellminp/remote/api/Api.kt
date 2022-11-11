package com.bellminp.remote.api

import com.bellminp.data.model.DataAutoLogin
import com.bellminp.remote.model.login.LoginResponse
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
        @Header("Authorization") Authorization: String,
    ): Response<GaugesTypeResponse>
}