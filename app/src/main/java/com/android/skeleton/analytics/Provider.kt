package com.android.skeleton.analytics

/**
 * List of analytics providers.
 * Our analytics architecture is oriented towards being able to achieve decoupling from any one
 * specific provider, so that the app can more easily have new providers added, or existing ones
 * deleted.
 */
enum class Provider {
    FIREBASE
}