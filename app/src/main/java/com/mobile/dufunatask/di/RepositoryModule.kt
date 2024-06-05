package com.mobile.dufunatask.di

import com.mobile.dufunatask.auth.data.remote.SignInService
import com.mobile.dufunatask.auth.data.repo.SignInRepository
import com.mobile.dufunatask.auth.data.repo.SignInRepositoryImpl
import com.mobile.dufunatask.auth.usecase.PersistenceUseCase
import com.mobile.dufunatask.professional_home.data.remote.TaskService
import com.mobile.dufunatask.professional_home.data.repo.TaskRepository
import com.mobile.dufunatask.professional_home.data.repo.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideSignInRepo(
        service: SignInService,
        persistenceUseCase: PersistenceUseCase
    ): SignInRepository = SignInRepositoryImpl(service, persistenceUseCase)

    @Provides
    @ViewModelScoped
    fun provideTaskRepo(
        service: TaskService,
        token: String,
        userId: Int
    ) : TaskRepository = TaskRepositoryImpl(service, token, userId)
}