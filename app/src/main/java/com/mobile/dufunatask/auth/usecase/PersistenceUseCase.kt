package com.mobile.dufunatask.auth.usecase

import android.content.SharedPreferences
import com.mobile.dufunatask.utils.Constants.AUTH_TOKEN
import com.mobile.dufunatask.utils.Constants.USERNAME_KEY
import com.mobile.dufunatask.utils.Constants.USER_ID
import javax.inject.Inject

class PersistenceUseCase @Inject constructor(
    private val editor: SharedPreferences.Editor,
    private val sharedPreferences: SharedPreferences
) {

    fun saveAuthToken(token: String) = with(editor) {
        putString(AUTH_TOKEN, token)
        apply()
    }

    fun saveUserName(username: String) = with(editor){
        putString(USERNAME_KEY, username)
        apply()
    }

    fun saveUserId(userId: String) = with(editor){
        putString(USER_ID, userId)
        apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken() = sharedPreferences.getString(AUTH_TOKEN, null) ?: "null"

    /**
     * Function to fetch username
     */
    fun fetchUsername() = sharedPreferences.getString(USERNAME_KEY, null) ?: "null"

    /**
     * Function to fetch id
     */
    fun fetchUserId() = sharedPreferences.getString(USER_ID, null) ?: "0"
}