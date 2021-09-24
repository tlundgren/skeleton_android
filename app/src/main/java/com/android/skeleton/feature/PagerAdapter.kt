package com.android.skeleton.feature

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.skeleton.feature.list.FragmentList
import java.lang.IndexOutOfBoundsException

const val INDEX_HEADER = 0
const val INDEX_LIST = 1

/**
 * Adapter for [FragmentPager]. Holds fragments: [FragmentList], [FragmentHeader].
 */
class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val tabFragmentCreators: Map<Int, () -> Fragment> = mapOf(
        INDEX_LIST to { FragmentList() },
        INDEX_HEADER to { FragmentHeader() }
    )

    override fun getItemCount() = tabFragmentCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}