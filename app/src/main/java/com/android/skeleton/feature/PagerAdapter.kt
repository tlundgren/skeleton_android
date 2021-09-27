package com.android.skeleton.feature

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.skeleton.feature.list.FragmentList
import com.android.skeleton.feature.recipe.FragmentRecipes
import java.lang.IndexOutOfBoundsException

const val INDEX_LIST = 0
const val INDEX_HEADER = 1
const val INDEX_RECIPES = 2

/**
 * Adapter for [FragmentPager]. Holds fragments: [FragmentList], [FragmentHeader], [FragmentRecipes].
 */
class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val tabFragmentCreators: Map<Int, () -> Fragment> = mapOf(
        INDEX_LIST to { FragmentList() },
        INDEX_HEADER to { FragmentHeader() },
        INDEX_RECIPES to { FragmentRecipes() },
    )

    override fun getItemCount() = tabFragmentCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}