package com.mobile.dufunatask.auth.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiError (
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code : Int,
    @SerializedName("message")
    val message: String
)