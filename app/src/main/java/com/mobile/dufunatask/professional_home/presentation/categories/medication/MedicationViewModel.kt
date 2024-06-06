package com.mobile.dufunatask.professional_home.presentation.categories.medication

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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicationViewModel @Inject constructor(
    private val taskRepo: TaskRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _medicationTasks = MutableLiveData<Result<SignInResult<List<TaskData>>>>()
    val medicationTasks: LiveData<Result<SignInResult<List<TaskData>>>> = _medicationTasks

    init {
        getMedicationTasks()
    }

    private fun getMedicationTasks() = viewModelScope.launch {
        taskRepo.getMedicationTask().flowOn(ioDispatcher)
            .onStart {
            _medicationTasks.value = Result.Loading(Pair(true, null))
        }.catch {
            _medicationTasks.value = Result.Error(message = it.processNetworkError())
        }.collectLatest {
            _medicationTasks.value = Result.Success(it)
        }
    }
}

