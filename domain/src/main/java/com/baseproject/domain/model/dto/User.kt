package com.baseproject.domain.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: Int,
    var firstName: String,
    var lastName: String
) : Parcelable