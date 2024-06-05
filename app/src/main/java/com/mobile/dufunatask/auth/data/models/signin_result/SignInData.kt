package com.mobile.dufunatask.auth.data.models.signin_result

import com.google.gson.annotations.SerializedName

data class SignInData(
    @SerializedName("user")
    val signedInUser: SignedInUser,
    val userToken: UserToken
)