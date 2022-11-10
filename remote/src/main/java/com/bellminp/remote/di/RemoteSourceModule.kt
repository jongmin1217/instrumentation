package com.bellminp.remote.di

import com.bellminp.data.remote.RemoteDataSource
import com.bellminp.remote.RemoteDataSourceImpl
import com.bellminp.remote.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {

    @Provides
    @Singleton
    fun provideWJRemoteDataSource(api: Api): RemoteDataSource {
        return RemoteDataSourceImpl(api)
    }
}