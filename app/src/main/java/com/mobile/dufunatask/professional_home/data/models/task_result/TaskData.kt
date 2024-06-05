package com.mobile.dufunatask.professional_home.data.models.task_result

data class TaskData(
    val action: String,
    val hourOfDay: String?,
    val isAssigned: Boolean,
    val noSupportPersonnel: Int,
    val order: String?,
    val priority: String,
    val supportLevel: String?,
    val supportPersonnel: String,
    val taskAssignments: List<TaskAssignment>?,
    val taskDate: String,
    val taskDetailRef: String,
    val taskEndedOn: Any,
    val taskGroup: String,
    val taskId: String,
    val taskScheduleId: String,
    val taskStartedOn: Any,
    val taskType: String,
    val timeOfDay: String,
    val userId: Int,
    val workStatus: Any
)