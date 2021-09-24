package com.android.skeleton.di

import android.content.Context
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import com.android.skeleton.data.repository.RepositoryItem

/**
 * Factory that provides repositories via dependency injection.
 */
class FactoryRepository {
    /**
     * Singleton.
     */
    fun getRepositoryItem(context: Context): RepositoryItem {
        val entryPoint = EntryPointAccessors.fromApplication(
            context,
            RepositoryFactoryEntryPoint::class.java
        )
        return entryPoint.repositoryItem()
    }

    /**
     * Interface for Hilt to be able to provide repositories.
     */
    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface RepositoryFactoryEntryPoint {
        fun repositoryItem(): RepositoryItem
    }
}