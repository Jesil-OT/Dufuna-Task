package com.mobile.dufunatask.auth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.dufunatask.auth.data.models.signin_result.SignInData
import com.mobile.dufunatask.auth.data.models.signin_result.SignInResult
import com.mobile.dufunatask.auth.data.repo.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repo: SignInRepository
) : ViewModel() {

    private val _navAction = MutableSharedFlow<SignInNavigationEvent>()
    val navAction = _navAction.asSharedFlow()

    private val _username = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    private val _signInUserState = MutableLiveData<Result<SignInResult<SignInData>>>()
    val signInUserState: LiveData<Result<SignInResult<SignInData>>> = _signInUserState


    fun signIn(username: String, password: String) = viewModelScope.launch {
        repo.signIn(username, password)
            .onStart {
                _signInUserState.value = Result.Loading(Pair(false, "Signing you in…"))
            }
            .catch {
                _signInUserState.value = Result.Loading(Pair(true, "Sign in"))
                _signInUserState.value = Result.Error(message = it.processNetworkError())
            }
            .collect {
                _signInUserState.value = Result.Loading(Pair(true, "Sign in"))
                _signInUserState.value = Result.Success(data = it)
                navigateToHome()
            }
    }

    val isSignInEnable: Flow<Boolean> = combine(_username, _password) { username, password ->
        val isUsernameEmpty = username.isNotBlank()
        val isPasswordEmpty = password.isNotBlank()
        val isPasswordCorrect = password.isValidPassword()
        return@combine isUsernameEmpty and isPasswordEmpty and isPasswordCorrect
    }

    private fun navigateToHome() = viewModelScope.launch {
        _navAction.emit(SignInNavigationEvent.Navigate(SignInFragmentDirections.toHomeFragment()))
    }

    fun setUsername(username: String) {
        _username.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    private fun String.isValidPassword(): Boolean {
        val hasDigitOrSpecialChar = this.any { !it.isLetter() && !it.isDigit() }
        val hasLowercase = this.any { it.isLowerCase() }
        val hasUppercase = this.any { it.isUpperCase() }
        val hasValidLength = this.length > 8
        return hasDigitOrSpecialChar && hasLowercase && hasUppercase && hasValidLength
    }

}