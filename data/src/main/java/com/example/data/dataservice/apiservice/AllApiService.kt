package com.example.data.dataservice.apiservice

import com.example.entity.responcemodel.DataResponse
import retrofit2.Response
import retrofit2.http.*

interface AllApiService {

    @GET("posts/{id}")
    suspend fun getUserData(@Path("id") id:Long): Response<DataResponse>

}
