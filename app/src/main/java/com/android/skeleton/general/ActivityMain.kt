package com.android.skeleton.general

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import com.android.skeleton.R
import com.android.skeleton.analytics.UserIdGenerator
import com.android.skeleton.di.FactoryAnalytics
import timber.log.Timber

@AndroidEntryPoint
class ActivityMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUser()
    }

    /**
     * Recovers the current user id and reports it to analytics.
     * If the user has no id, it gets one and stores it in shared preferences.
     */
    private fun setUser() {
        val preferenceUserid = "com.android.skeleton.userid"
        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val userId = preferences.getString(preferenceUserid, null)
        userId?.let {
            val newUserId = UserIdGenerator().get()
            FactoryAnalytics().getSender(applicationContext).setUser(newUserId)
            Timber.d("User id for analytics set to: $newUserId.")
            with(preferences.edit()) {
                putString(preferenceUserid, newUserId)
                apply()
            }
        }
    }
}