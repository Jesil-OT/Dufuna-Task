package com.mobile.dufunatask.professional_home.presentation

import androidx.lifecycle.ViewModel
import com.mobile.dufunatask.auth.usecase.PersistenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    persistenceUseCase: PersistenceUseCase
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val getUsername = persistenceUseCase.fetchUsername()

    init {
        _username.value = getUsername
    }

}