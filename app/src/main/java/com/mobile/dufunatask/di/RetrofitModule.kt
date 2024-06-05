package com.mobile.dufunatask.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobile.dufunatask.utils.Constants.BASE_URL
import com.mobile.dufunatask.auth.data.remote.SignInService
import com.mobile.dufunatask.auth.data.repo.SignInRepository
import com.mobile.dufunatask.auth.data.repo.SignInRepositoryImpl
import com.mobile.dufunatask.auth.usecase.PersistenceUseCase
import com.mobile.dufunatask.utils.Constants.CONNECTION_TIME_OUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object RetrofitModule {

    @Provides
    @ViewModelScoped
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @ViewModelScoped
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @ViewModelScoped
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .build()

    @Provides
    @ViewModelScoped
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
}