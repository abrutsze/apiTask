package com.example.domian.interactors

import com.example.entity.Result
import com.example.entity.localmodels.DataModel


interface GetDataInteractor {
  suspend  fun getDataResponse(id:String): Result<DataModel>
}