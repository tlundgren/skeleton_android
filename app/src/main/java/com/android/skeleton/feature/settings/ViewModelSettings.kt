package com.android.skeleton.feature.settings

import android.content.Context
import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Supports [FragmentSettings].
 */
@HiltViewModel
class ViewModelSettings @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {
    private val preferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    /**
     * @return the value of setting y set by the user as default, the app default if none set
     */
    fun settingY(): String {
        return preferences.getString(PREFERENCE_SETTINGY, DEFAULT_SETTINGY)
            ?: DEFAULT_SETTINGY
    }

    /**
     * Changes parameter Y.
     */
    fun changeSettingY(settingY: Editable?) {
        // validate user input if necessary
        val setting = settingY?.toString() ?: ""
        with(preferences.edit()) {
            putString(PREFERENCE_SETTINGY, setting)
            apply()
        }
    }

}