package com.example.entity.responcemodel

import com.squareup.moshi.Json


data class DataResponse(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "userId")
    var userId: Int? = null,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "body")
    var body: String? = null,
)