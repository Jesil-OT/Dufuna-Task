package com.mobile.dufunatask.professional_home.data.remote

import com.mobile.dufunatask.auth.data.models.signin_result.SignInResult
import com.mobile.dufunatask.professional_home.data.models.task_result.TaskData
import com.mobile.dufunatask.utils.Constants.TASK_URL
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TaskService {

    @GET(TASK_URL)
    suspend fun getTask(
        @Header("Authorization") token: String,
        @Path("shortCode") shortCode: String,
        @Path("careHomeId") careHomeId: String,
        @Query("assignee") assignee: String
    ): SignInResult<List<TaskData>>
}