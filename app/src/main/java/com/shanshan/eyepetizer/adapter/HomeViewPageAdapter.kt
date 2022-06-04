package com.shanshan.eyepetizer.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPageAdapter : FragmentStateAdapter {

    private var mFragments: List<Fragment> = ArrayList()

    constructor(fm: FragmentManager, lifecycle: Lifecycle) : super(fm, lifecycle)

    constructor(
        fm: FragmentManager,
        lifecycle: Lifecycle,
        fragmentList: List<Fragment>,
    ) : super(fm, lifecycle) {
        this.mFragments = fragmentList
    }

    override fun getItemCount(): Int {
        return mFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }
}