package com.android.skeleton.di

import android.content.Context
import com.android.skeleton.analytics.sender.Sender
import com.android.skeleton.analytics.sender.SenderSimple
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

/**
 * Factory that provides [Sender].
 */
// This factory has Hilt instantiate a [Sender] in the singleton component so that only one
// instance of [Sender] is ever created, and clients reuse that instance.
class FactoryAnalytics {

    fun getSender(context: Context): Sender {
        val entryPoint = EntryPointAccessors.fromApplication(
            context,
            SenderFactoryEntryPoint::class.java
        )
        return entryPoint.sender()
    }

    /**
     * Interface for Hilt to be able to provide analytics [Sender]s.
     */
    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface SenderFactoryEntryPoint {
        fun sender(): SenderSimple
    }
}
