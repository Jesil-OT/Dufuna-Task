package com.mobile.dufunatask.professional_home.presentation.categories.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.dufunatask.auth.data.models.signin_result.SignInResult
import com.mobile.dufunatask.auth.presentation.Result
import com.mobile.dufunatask.auth.presentation.processNetworkError
import com.mobile.dufunatask.professional_home.data.models.task_result.TaskData
import com.mobile.dufunatask.professional_home.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivitiesViewModel @Inject constructor(
    private val taskRepo: TaskRepository
) : ViewModel() {

    private val _activitiesTasks = MutableLiveData<Result<SignInResult<List<TaskData>>>>()
    val activitiesTasks: LiveData<Result<SignInResult<List<TaskData>>>> = _activitiesTasks

    init {
        getActivitiesTasks()
    }

    private fun getActivitiesTasks() = viewModelScope.launch {
        taskRepo.getActivitiesTask().onStart {
            _activitiesTasks.value = Result.Loading(Pair(true, null))
        }.catch {
            _activitiesTasks.value = Result.Error(message = it.processNetworkError())
        }.collectLatest {
            _activitiesTasks.value = Result.Success(it)
        }
    }

}