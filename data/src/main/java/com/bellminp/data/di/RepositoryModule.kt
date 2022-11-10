package com.bellminp.data.di

import com.bellminp.data.LocalRepositoryImpl
import com.bellminp.data.RemoteRepositoryImpl
import com.bellminp.data.local.LocalDataSource
import com.bellminp.data.remote.RemoteDataSource
import com.bellminp.domain.repository.LocalRepository
import com.bellminp.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalRepository(
        localDataSource: LocalDataSource
    ): LocalRepository {
        return LocalRepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(
        remoteDataSource: RemoteDataSource
    ): RemoteRepository {
        return RemoteRepositoryImpl(remoteDataSource)
    }
}