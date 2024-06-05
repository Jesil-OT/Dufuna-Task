package com.mobile.dufunatask.utils

object Constants {
    const val BASE_URL = "https://api.staging.caresaas.co.uk/caresaas/v1/services/"
    const val SHARED_PREF_NAME = "CaresassPrefFile"
    const val USERNAME_KEY = "username"
    const val AUTH_TOKEN = "Token"
    const val USER_ID = "UserId"

    // connection timeout
    const val CONNECTION_TIME_OUT : Long = 60
    const val MILLIS_IN_FUTURE : Long= 180_000
    const val COUNT_DOWN_INTERVAL : Long = 100

    //Path Strings
    const val SHORT_CODE = "FKRC"
    const val CARE_HOME_ID = "2"

    //Api EndPoints
    const val SIGN_IN_URL = "auth/login"
    const val TASK_URL = "tasks/{shortCode}/careHome/{careHomeId}"

    //Tags
    const val MEDICATION_TAG = "MedicationFragment"
    const val ACTIVITIES_TAG = "ActivitiesFragment"

}