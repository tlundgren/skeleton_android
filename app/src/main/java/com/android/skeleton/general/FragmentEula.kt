package com.android.skeleton.general

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.skeleton.databinding.FragmentEulaBinding

/**
 * Presents the eula to the user: if they accept, navigates to pager; else, finishes the app.
 */
class FragmentEula : Fragment() {
    private lateinit var preferences: SharedPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)
        preferences = requireActivity().getSharedPreferences(
            SHARED_PREFERENCES_EULA,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEulaBinding.inflate(inflater)
        // responses to user interactions
        binding.frEulaAccept.setOnClickListener {
            clickedButtonAccept()
        }
        binding.frEulaDecline.setOnClickListener {
            clickedButtonDecline()
        }

        return binding.root
    }

    /**
     * Stores user rejection. Closes the app.
     */
    private fun clickedButtonDecline() {
        with(preferences.edit()) {
            putBoolean(PREFERENCE_EULA_ACCEPTED, false)
            apply()
        }
        requireActivity().finish()
    }

    /**
     * Stores user agreement. Navigates to fragment pager.
     */
    private fun clickedButtonAccept() {
        with(preferences.edit()) {
            putBoolean(PREFERENCE_EULA_ACCEPTED, true)
            apply()
        }
        findNavController().navigate(FragmentEulaDirections.actionFragmentEulaToFragmentPager())
    }

}