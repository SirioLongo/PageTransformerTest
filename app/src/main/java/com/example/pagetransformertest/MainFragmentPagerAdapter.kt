package com.example.pagetransformertest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainFragmentPagerAdapter(
    activity: FragmentActivity,
    private val pageCount: Int,
    private val fragmentProvider: MainFragmentProvider
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = pageCount

    override fun createFragment(position: Int): Fragment {
        return fragmentProvider.getFragment(position)
    }
}