package com.bellminp.remote.api

import com.bellminp.data.model.DataAutoLogin
import com.bellminp.remote.model.login.LoginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {

    @POST("/user/login")
    suspend fun login(
        @Body dataAutoLogin: DataAutoLogin
    ): Response<LoginResponse>
}