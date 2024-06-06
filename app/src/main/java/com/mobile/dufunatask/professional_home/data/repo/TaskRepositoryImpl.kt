package com.mobile.dufunatask.professional_home.data.repo

import com.mobile.dufunatask.auth.data.models.signin_result.SignInResult
import com.mobile.dufunatask.professional_home.data.models.task_result.TaskData
import com.mobile.dufunatask.professional_home.data.remote.TaskService
import com.mobile.dufunatask.utils.Constants.CARE_HOME_ID
import com.mobile.dufunatask.utils.Constants.SHORT_CODE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskService: TaskService,
    private val token: String,
    private val userId: Int
) : TaskRepository {

    override suspend fun getMedicationTask() = flow {
        val medicationTask = taskService.getTask(
            token = token,
            shortCode = SHORT_CODE,
            careHomeId = CARE_HOME_ID,
            assignee = userId.toString()
        )
        emit(medicationTask)
    }

    override suspend fun getActivitiesTask() = flow {
        val activityTask = taskService.getTask(
            token = token,
            shortCode = SHORT_CODE,
            careHomeId = CARE_HOME_ID,
            assignee = userId.toString()
        )
        emit(activityTask)
    }

}