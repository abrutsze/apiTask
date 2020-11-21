package com.example.domian.usecase

import com.example.data.datastore.GetDataRepository
import com.example.domian.interactors.GetDataInteractor
import com.example.domian.utils.toLocalData
import com.example.entity.CallException
import com.example.entity.Constants.Companion.ERROR_NULL_DATA
import com.example.entity.Constants.Companion.INPUT_DATA_EMPTY
import com.example.entity.Result
import com.example.entity.localmodels.DataModel

class GetDataUseCase(private val getDataRepository: GetDataRepository) :
    GetDataInteractor {

    override suspend fun getDataResponse(id: String): Result<DataModel> {
        if (id.isEmpty()) {
            return Result.Error(CallException(INPUT_DATA_EMPTY, null, "Input number is empty"))
        }
        return when (val apiData = getDataRepository.getDataListResponse(id.toLong())) {
            is Result.Success -> {
                val mappingData = apiData.data?.toLocalData()
                Result.Success(mappingData)
            }
            else -> {
                Result.Error(CallException(ERROR_NULL_DATA, null, "Can't load data from server"))
            }
        }
    }

}