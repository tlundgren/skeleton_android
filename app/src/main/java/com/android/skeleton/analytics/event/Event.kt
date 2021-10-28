package com.android.skeleton.analytics.event

import com.android.skeleton.analytics.Provider

/**
 * An event to capture, relevant for analysis.
 * [Event]s provide a contract for event logging, thus helping ensure analytics data is structured
 * as intended.
 */
abstract class Event (
    val name: String,
    val params: Map<String, String> = emptyMap(),
    /**
     * The list of analytics providers for which this event is relevant.
     */
    val providers: List<Provider> = emptyList()
)