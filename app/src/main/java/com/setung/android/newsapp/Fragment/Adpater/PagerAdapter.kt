package com.setung.android.newsapp.Fragment.Adpater

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.setung.android.newsapp.Fragment.*


class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 7
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                val fg = AllFragment()
                AllFragment.currentFragment = fg
                return fg
            }

            1 -> {
                val fg = AllFragment(category = "business")
                AllFragment.currentFragment = fg
                return fg
            }

            2 -> {
                val fg = AllFragment(category = "entertainment")
                AllFragment.currentFragment = fg
                return fg
            }
            3 -> {
                val fg = AllFragment(category = "health")
                AllFragment.currentFragment = fg
                return fg
            }
            4 -> {
                val fg = AllFragment(category = "science")
                AllFragment.currentFragment = fg
                return fg
            }
            5 -> {
                val fg = AllFragment(category = "sports")
                AllFragment.currentFragment = fg
                return fg
            }
            6 -> {
                val fg = AllFragment(category = "technology")
                AllFragment.currentFragment = fg
                return fg
            }
        }
        return AllFragment()
    }

}