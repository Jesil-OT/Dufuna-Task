package com.mobile.dufunatask.di

import com.mobile.dufunatask.auth.data.remote.SignInService
import com.mobile.dufunatask.auth.usecase.PersistenceUseCase
import com.mobile.dufunatask.professional_home.data.remote.TaskService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {

    @Provides
    @ViewModelScoped
    fun provideSignInService(retrofit: Retrofit): SignInService = retrofit.create(
        SignInService::class.java
    )

    @Provides
    @ViewModelScoped
    fun provideTaskService(retrofit: Retrofit): TaskService = retrofit.create(
        TaskService::class.java
    )


    @Provides
    @ViewModelScoped
    fun provideApiKey(persistenceUseCase: PersistenceUseCase): String =
        "Bearer ${persistenceUseCase.fetchAuthToken()}"

    @Provides
    @ViewModelScoped
    fun provideUserId(persistenceUseCase: PersistenceUseCase): Int =
        persistenceUseCase.fetchUserId().toInt()
}