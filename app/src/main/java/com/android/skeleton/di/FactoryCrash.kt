package com.android.skeleton.di

import com.android.skeleton.crash.Context
import com.android.skeleton.crash.ContextSimple
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

/**
 * Factory that provides [Context].
 */
// This factory has Hilt instantiate a [Context] in the singleton component so that only one
// instance of [Context] is ever created, and clients reuse that instance.
class FactoryCrash {
    fun getContext(context: android.content.Context): Context {
        val entryPoint = EntryPointAccessors.fromApplication(
            context,
            ContextFactoryEntryPoint::class.java
        )
        return entryPoint.context()
    }

    /**
     * Interface for Hilt to be able to provide analytics [Sender]s.
     */
    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface ContextFactoryEntryPoint {
        fun context(): ContextSimple
    }
}