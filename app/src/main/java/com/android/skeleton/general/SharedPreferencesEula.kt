package com.android.skeleton.general

/**
 * Shared preference to store EULA related data.
 */
const val SHARED_PREFERENCES_EULA = "com.android.skeleton.eula_preferences"

/**
 * Attribute to track is user accepted the EULA.
 * If the version in this attribute is changed, the app will ask for the user acceptance once more.
 */
const val PREFERENCE_EULA_ACCEPTED = "com.android.skeleton.eulaV1Accepted"