package com.android.skeleton.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.android.skeleton.data.feature.DatabaseApp
import com.android.skeleton.data.feature.DaoItem
import javax.inject.Singleton

/**
 * Dependency injection binding to tell Hilt how to instantiate a [DatabaseApp].
 */
@Module
@InstallIn(SingletonComponent::class)
object ModuleDatabaseApp {
    @Singleton
    @Provides
    fun provideDatabaseApp(@ApplicationContext context: Context): DatabaseApp {
        return DatabaseApp.getInstance(context)
    }

    @Provides
    fun provideDaoItem(databaseApp: DatabaseApp): DaoItem {
        return databaseApp.daoItem
    }
}