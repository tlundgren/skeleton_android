package com.android.skeleton.di

import android.content.Context
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import com.android.skeleton.data.repository.RepositoryItem
import com.android.skeleton.data.repository.RepositoryRecipe

/**
 * Factory that provides repositories.
 */
// This factory asks Hilt to install the repositories in the Singleton Component in order for them
// to be singletons.
class FactoryRepository {
    fun getRepositoryItem(context: Context): RepositoryItem {
        val entryPoint = EntryPointAccessors.fromApplication(
            context,
            RepositoryFactoryEntryPoint::class.java
        )
        return entryPoint.repositoryItem()
    }

    fun getRepositoryRecipe(context: Context): RepositoryRecipe {
        val entryPoint = EntryPointAccessors.fromApplication(
            context,
            RepositoryFactoryEntryPoint::class.java
        )
        return entryPoint.repositoryRecipe()
    }

    /**
     * Interface for Hilt to be able to provide repositories.
     */
    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface RepositoryFactoryEntryPoint {
        fun repositoryItem(): RepositoryItem
        fun repositoryRecipe(): RepositoryRecipe
    }
}
