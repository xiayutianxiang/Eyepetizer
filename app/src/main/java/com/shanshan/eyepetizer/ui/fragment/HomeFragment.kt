package com.shanshan.eyepetizer.ui.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.HomeViewPageAdapter
import com.shanshan.eyepetizer.databinding.FragmentHomeBinding
import com.shanshan.eyepetizer.ui.fragment.homepage.DailyFragment
import com.shanshan.eyepetizer.ui.fragment.homepage.DiscoveryFragment
import com.shanshan.eyepetizer.ui.fragment.homepage.RecommendFragment
import com.shanshan.eyepetizer.ui.view.CommTabLayout
import com.shanshan.eyepetizer.utils.ResourceUtils

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragmentWithViewPager() {

    private var tabList: ArrayList<String>? = null

    override fun initFragments() {
        fragments.add(DiscoveryFragment.newInstance())
        fragments.add(RecommendFragment.newInstance())
        fragments.add(DailyFragment.newInstance())
    }

    override fun initTabLists() {
        tabList = arrayListOf(
            ResourceUtils.getString(R.string.home_find),
            ResourceUtils.getString(R.string.home_recommend),
            ResourceUtils.getString(R.string.home_daily)
        )
        binding.homeTab.setTabLayoutTitles(tabList!!)
    }
}