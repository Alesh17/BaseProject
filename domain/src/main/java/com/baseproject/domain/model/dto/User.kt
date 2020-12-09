package com.baseproject.domain.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: Int,
    var firstName: String,
    var lastName: String
) : Parcelable