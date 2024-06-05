package com.mobile.dufunatask.auth.data.repo

import com.mobile.dufunatask.auth.data.models.signin_result.SignInData
import com.mobile.dufunatask.auth.data.models.signin_result.SignInResult
import kotlinx.coroutines.flow.Flow

interface SignInRepository {

    suspend fun signIn(username: String, password: String): Flow<SignInResult<SignInData>>
}