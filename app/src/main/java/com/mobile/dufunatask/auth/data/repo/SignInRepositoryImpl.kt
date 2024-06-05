package com.mobile.dufunatask.auth.data.repo

import com.mobile.dufunatask.auth.data.models.SignInModels
import com.mobile.dufunatask.auth.data.remote.SignInService
import com.mobile.dufunatask.auth.usecase.PersistenceUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInService: SignInService,
    private val persistenceUseCase: PersistenceUseCase
) : SignInRepository {

    override suspend fun signIn(username: String, password: String) = flow {
        val sigInFields = SignInModels(username = username, password = password)
        val signInUser = signInService.signIn(sigInFields)
        persistenceUseCase.apply{
            saveAuthToken(signInUser.loginData.userToken.accessToken)
            saveUserName(signInUser.loginData.signedInUser.name)
            saveUserId(signInUser.loginData.signedInUser.userId)
        }
        emit(signInUser)
    }
}