package com.mobile.dufunatask.auth.data.models.signin_result

import com.google.gson.annotations.SerializedName

data class SignInResult<T>(
    val code: Int,
    @SerializedName("data")
    val loginData : T,
    val message: String,
    val status: String
)