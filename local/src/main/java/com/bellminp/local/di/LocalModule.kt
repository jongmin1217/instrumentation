package com.bellminp.local.di

import android.content.Context
import com.bellminp.local.prefs.PrefsHelper
import com.bellminp.local.prefs.PrefsHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext applicationContext: Context): PrefsHelper {
        return PrefsHelperImpl(applicationContext)
    }
}