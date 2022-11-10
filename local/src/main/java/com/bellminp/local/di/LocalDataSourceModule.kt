package com.bellminp.local.di

import com.bellminp.data.local.LocalDataSource
import com.bellminp.local.LocalDataSourceImpl
import com.bellminp.local.prefs.PrefsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        prefsHelper: PrefsHelper
    ): LocalDataSource {
        return LocalDataSourceImpl(prefsHelper)
    }
}