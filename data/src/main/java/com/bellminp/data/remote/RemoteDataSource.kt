package com.bellminp.data.remote

import com.bellminp.data.model.DataAutoLogin
import com.bellminp.data.model.DataLogin
import retrofit2.Response

interface RemoteDataSource {

    suspend fun login(login : DataAutoLogin) : Response<DataLogin>
}