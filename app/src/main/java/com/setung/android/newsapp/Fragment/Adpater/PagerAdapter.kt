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
                return AllFragment()
            }

            1 -> {
                return AllFragment(category = "business")
            }

            2 -> {
                return AllFragment(category = "entertainment")
            }
            3 -> {
                return AllFragment(category = "health")
            }
            4 -> {
                return AllFragment(category = "science")
            }
            5 -> {
                return AllFragment(category = "sports")
            }
            6 -> {
                return AllFragment(category = "technology")
            }
        }
        return AllFragment()
    }

}