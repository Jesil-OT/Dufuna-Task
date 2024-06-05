package com.mobile.dufunatask.di

import android.content.Context
import android.content.SharedPreferences
import com.mobile.dufunatask.utils.Constants.SHARED_PREF_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences
            = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideEditor(sharedPreferences: SharedPreferences) : SharedPreferences.Editor = sharedPreferences.edit()
}