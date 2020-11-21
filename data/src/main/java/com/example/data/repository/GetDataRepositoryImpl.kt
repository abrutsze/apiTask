package com.example.data.repository

import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.datastore.GetDataRepository
import com.example.data.utils.analyzeResponse
import com.example.data.utils.makeApiCall
import com.example.entity.responcemodel.DataResponse
import retrofit2.Response
import com.example.entity.Result

class GetDataRepositoryImpl(private val allApiService: AllApiService) :
    GetDataRepository {

    override suspend fun getDataListResponse(id:Long): Result<DataResponse> =
        makeApiCall({
            getUserData(
                allApiService.getUserData(id)
            )
        })

    private fun getUserData(popularMove: Response<DataResponse>): Result<DataResponse> =
        analyzeResponse(popularMove)
}