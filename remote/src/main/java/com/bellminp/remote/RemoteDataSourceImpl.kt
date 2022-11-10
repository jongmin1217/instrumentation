package com.bellminp.remote

import com.bellminp.data.model.DataAutoLogin
import com.bellminp.data.model.DataLogin
import com.bellminp.data.remote.RemoteDataSource
import com.bellminp.remote.api.Api
import com.bellminp.remote.mapper.loginToData
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: Api
) : RemoteDataSource{

    override suspend fun login(login: DataAutoLogin): Response<DataLogin> {
        return try {
            val response = api.login(login)
            if(response.isSuccessful){
                Response.success(response.code(),response.body()?.loginToData())
            }else{
                Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            }
        }catch (e : Exception){
            Response.error(500, e.toString().toResponseBody(null))
        }

    }
}