package com.mobile.dufunatask.auth.data.models

import com.google.gson.annotations.SerializedName

data class SignInModels(
    @SerializedName("userName")
    val username: String,
    @SerializedName("password")
    val password: String
)