package com.example.githubexplorer.repositoryScreen.application.view

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.githubexplorer.R
import com.example.githubexplorer.repositoryScreen.application.view.adapter.RepositoryActivityViewPagerAdapter
import com.google.android.material.tabs.TabLayout

//Activity with 2 fragments (one for list of devices and the second for groups)
class RepositoryActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager

    /**
     * OnCreate overridden function
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_search)
        initViewPager()
        setUpViewPager()
    }

    /**
     * function to initialize viewpager and tablayout
     */
    private fun initViewPager() {
        viewPager = findViewById(R.id.view_pager_main_activity)
    }

    /**
     * function to set viewpager adapter
     * also sets the tab layout with the viewpager
     */
    private fun setUpViewPager() {
        viewPager.adapter = RepositoryActivityViewPagerAdapter(supportFragmentManager)
    }

    /**
     * Functions to hide the keyboard
     */
    private fun hideKeyboard() {
        // Check if no view has focus
        val view = currentFocus
        if (view != null) {
            val inputManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

}