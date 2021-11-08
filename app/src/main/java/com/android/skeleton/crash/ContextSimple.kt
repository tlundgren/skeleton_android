package com.android.skeleton.crash

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import javax.inject.Inject

/**
 * [Context] that relays data to Crashlytics.
 */
class ContextSimple @Inject constructor(): Context {
    private val crashlytics = Firebase.crashlytics

    override fun add(key: String, value: String) {
        crashlytics.setCustomKey(key, value)
    }

    override fun add(key: String, value: Int) {
        crashlytics.setCustomKey(key, value)
    }

    override fun add(key: String, value: Boolean) {
        crashlytics.setCustomKey(key, value)
    }

}