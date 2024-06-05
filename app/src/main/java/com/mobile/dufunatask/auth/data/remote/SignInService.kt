package com.mobile.dufunatask.auth.data.remote

import com.mobile.dufunatask.auth.data.models.SignInModels
import com.mobile.dufunatask.auth.data.models.signin_result.SignInData
import com.mobile.dufunatask.auth.data.models.signin_result.SignInResult
import com.mobile.dufunatask.utils.Constants.SIGN_IN_URL
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {

    @POST(SIGN_IN_URL)
    suspend fun signIn(
        @Body signInFields: SignInModels
    ) : SignInResult<SignInData>
}