package com.mobile.dufunatask.professional_home.data.repo

import com.mobile.dufunatask.auth.data.models.signin_result.SignInResult
import com.mobile.dufunatask.professional_home.data.models.task_result.TaskData
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun getMedicationTask() : Flow<SignInResult<List<TaskData>>>

    suspend fun getActivitiesTask(): Flow<SignInResult<List<TaskData>>>
}