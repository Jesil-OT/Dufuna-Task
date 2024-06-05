package com.mobile.dufunatask.auth.data.models.signin_result

import com.google.gson.annotations.SerializedName

data class UserToken(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)