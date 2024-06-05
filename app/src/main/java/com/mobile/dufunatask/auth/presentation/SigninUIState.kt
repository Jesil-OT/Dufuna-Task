package com.mobile.dufunatask.auth.presentation

import androidx.navigation.NavDirections
import com.google.gson.Gson
import com.mobile.dufunatask.auth.data.models.ApiError
import retrofit2.HttpException
import java.io.FileNotFoundException
import java.io.IOException
import java.net.MalformedURLException
import java.net.SocketTimeoutException

sealed interface SignInNavigationEvent {
    data class Navigate(val destination: NavDirections) : SignInNavigationEvent
}

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null,
    val state: Pair<Boolean, String?> = Pair(false, "")
) {
    class Success<T>(data: T?) : Result<T>(data)
    class Error<T>(data: T? = null, message: String) : Result<T>(data, message)
    class Loading<T>(state: Pair<Boolean, String?>) : Result<T>(state = state)
}

fun Throwable.processNetworkError(): String {
    this.let {
        it.printStackTrace()
        val message: String = try {
            when (it) {
                is HttpException -> {
                    val errorBody =
                        it.response()?.errorBody()?.string()
                    val err = Gson().fromJson(errorBody, ApiError::class.java)
                    err.message
                }

                is FileNotFoundException -> "data resource Not Found"
                is SocketTimeoutException -> "looks like this took longer to load, check your internet and try again"
                is MalformedURLException -> "Malformed URL, Report to Developer"
                is IOException -> "Opps, Seems you have a bad Internet Connection, please check and try again"
                else -> "Error signing in ${this.message}"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Service Unavailable"
        }
        return message
    }
}


