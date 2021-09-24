package com.android.skeleton.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.android.skeleton.data.feature.AppDatabase
import javax.inject.Singleton

/**
 * Dependency injection binding to tell Hilt how to instantiate an AppDatabase.
 */
@Module
@InstallIn(SingletonComponent::class)
object ModuleAppDatabase {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}