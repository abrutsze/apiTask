package com.example.entity.localmodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModel (
    var id: Int? = null,
    var userId: Int? = null,
    var title: String? = null,
    var body: String? = null,
) : Parcelable