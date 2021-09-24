package com.android.skeleton.feature

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.android.skeleton.R
import com.android.skeleton.databinding.FragmentPagerBinding
import com.android.skeleton.general.PREFERENCE_EULA_ACCEPTED
import com.android.skeleton.general.SHARED_PREFERENCES_EULA

/**
 * Fragment to hold fragments summary, and list.
 * Navigates automatically to fragment eula if the user has not accepted the license agreement.
 */
class FragmentPager : Fragment() {
    private var eulaAccepted = false
    private lateinit var preferences: SharedPreferences

    /**
     * If user has not agreed to EULA, navigates to fragment eula.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        preferences = requireActivity().getSharedPreferences(
            SHARED_PREFERENCES_EULA,
            Context.MODE_PRIVATE
        )
        eulaAccepted = preferences.getBoolean(PREFERENCE_EULA_ACCEPTED, false)
        if (!eulaAccepted)
            findNavController().navigate(FragmentPagerDirections.actionFragmentPagerToFragmentEula())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // save time by preventing inflation when pager is not to be displayed
        if (!eulaAccepted)
            return null

        val binding = FragmentPagerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.frPagerPager.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.frPagerTab, binding.frPagerPager) { tab, position ->
            tab.text = titleFor(position)
        }.attach()
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.frPagerToolbar)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_pager, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mn_fragmentabout -> {
                findNavController()
                    .navigate(FragmentPagerDirections.actionFragmentPagerToFragmentAbout())
                true
            }
            R.id.mn_fragmenthelp -> {
                findNavController()
                    .navigate(FragmentPagerDirections.actionFragmentPagerToFragmentHelp())
                true
            }
            R.id.mn_fragmentsettings -> {
                FragmentSettings().show(childFragmentManager, getString(R.string.frSettings_tag))
                true
            }
            else -> false
        }
    }

    private fun titleFor(position: Int): String? {
        return when (position) {
            INDEX_HEADER -> getString(R.string.frHeader_title)
            INDEX_LIST -> getString(R.string.frList_title)
            else -> null
        }
    }
}