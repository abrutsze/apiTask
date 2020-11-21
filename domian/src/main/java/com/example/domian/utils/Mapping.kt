package com.example.domian.utils


import com.example.entity.localmodels.*
import com.example.entity.responcemodel.*

fun DataResponse.toLocalData(): DataModel {
    return DataModel(
        id = id,
        userId = userId,
        title = title,
        body = body
    )
}



