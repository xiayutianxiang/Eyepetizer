package com.shanshan.eyepetizer.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.HomeViewPageAdapter
import com.shanshan.eyepetizer.databinding.FragmentCommunityBinding
import com.shanshan.eyepetizer.ui.fragment.communitypage.CommunityRecommendFragment
import com.shanshan.eyepetizer.ui.fragment.communitypage.CommunityFollowFragment
import com.shanshan.eyepetizer.ui.view.CommunityTabLayout
import com.shanshan.eyepetizer.utils.LogUtils

class CommunityFragment : Fragment() {

    private lateinit var binding: FragmentCommunityBinding

    private var fragments = ArrayList<Fragment>()
    private var homeAdapter: HomeViewPageAdapter? = null

    companion object {
        private const val TAG = "CommunityFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false)
        initFragment()
        initViewPager()

        return binding.root
    }

    private fun initFragment() {
        fragments.add(CommunityRecommendFragment.newInstance())
        fragments.add(CommunityFollowFragment.newInstance())
    }

    private fun initViewPager() {
        homeAdapter = HomeViewPageAdapter(requireActivity() ,fragments)
        binding.homePage.adapter = homeAdapter
        binding.homePage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                LogUtils.d(TAG, "position ---> $position")
                binding.communityTab.setCurSelected(position)
            }
        })

        binding.communityTab.setOnTabItemSelectedClick(object :
            CommunityTabLayout.OnTabItemSelectedClick {
            override fun tabItemSelected(position: Int) {
                LogUtils.d(TAG, "position ---> $position")
                binding.homePage.setCurrentItem(position, true)
            }
        })
    }
}