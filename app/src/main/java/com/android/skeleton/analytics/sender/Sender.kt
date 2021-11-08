package com.android.skeleton.analytics.sender

import com.android.skeleton.analytics.event.Event

/**
 * Sends [Event]s to analytics providers.
 * Decouples clients from any one provider. Thus, replacing, adding, removing providers becomes
 * transparent to clients.
 */
interface Sender {
    /**
     * Sends [event] to the analytics providers in the scope of the [event].
     */
    fun sendEvent(event: Event)

    /**
     * Sets the user for whom events will be sent.
     */
    fun setUser(user: String)
}