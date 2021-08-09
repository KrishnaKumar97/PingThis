package com.example.githubexplorer.repositoryScreen.application.view.adapter

import SavedRepositoryFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubexplorer.fragments.repositoryActivityFragments.SearchRepositoryFragment

/**
 * @param fragmentManager
 * adapter for viewpager
 */
@Suppress("DEPRECATION")
class RepositoryActivityViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    // Titles for tab layout
    private val tabTitles = arrayOf("Repositories", "Saved Repositories")

    /**
     * @param position
     * Returns the Fragment associated with a specified position
     */
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SearchRepositoryFragment()
            1 -> SavedRepositoryFragment()
            else -> SearchRepositoryFragment()
        }
    }

    /**
     * Returns the number of views available
     */
    override fun getCount(): Int {
        return 2
    }

    /**
     * @param position
     * Called by the ViewPager to obtain a title string to describe the specified page
     */
    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}