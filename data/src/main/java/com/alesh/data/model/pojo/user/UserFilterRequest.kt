package com.alesh.data.model.pojo.user

import com.google.gson.annotations.SerializedName

data class UserFilterRequest(
    @SerializedName("sort") var sort: String
)