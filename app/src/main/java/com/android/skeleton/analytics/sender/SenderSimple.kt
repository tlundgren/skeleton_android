package com.android.skeleton.analytics.sender

import android.os.Bundle
import com.android.skeleton.analytics.Provider
import com.android.skeleton.analytics.event.Event
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

/**
 * A simple [Sender] that can send events to the following analytics providers: Firebase.
 */
class SenderSimple @Inject constructor() : Sender {
    private val firebase = Firebase.analytics

    override fun sendEvent(event: Event) {
        if (event.providers.contains(Provider.FIREBASE)) {
            firebase.logEvent(event.name, event.params.toBundle())
        }
    }

}

fun Map<String, String>.toBundle(): Bundle {
    val bundle = Bundle()
    for ((key, value) in this) {
        bundle.putString(key, value)
    }
    return bundle
}