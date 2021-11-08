package com.android.skeleton.analytics

import java.util.*

/**
 * Generates identifiers for users.
 * This class is only meant to help illustrate how analytics services can be used with custom
 * user identifiers. In your app, you may just go with googles advertising id, or generate an id
 * in your backend.
 */
class UserIdGenerator() {
    fun get(): String {
        return UUID.randomUUID().toString()
    }
}