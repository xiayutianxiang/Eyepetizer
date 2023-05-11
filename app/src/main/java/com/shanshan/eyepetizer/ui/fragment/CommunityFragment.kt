package com.shanshan.eyepetizer.ui.fragment

import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.ui.fragment.communitypage.CommunityRecommendFragment
import com.shanshan.eyepetizer.ui.fragment.communitypage.CommunityFollowFragment
import com.shanshan.eyepetizer.utils.ResourceUtils

class CommunityFragment : BaseFragmentWithViewPager() {
    private var tabList: ArrayList<String>? = null

    override fun initFragments() {
        fragments.add(CommunityRecommendFragment.newInstance())
        fragments.add(CommunityFollowFragment.newInstance())
    }

    override fun initTabLists() {
        tabList = arrayListOf(
            ResourceUtils.getString(R.string.community_recommend),
            ResourceUtils.getString(R.string.community_follow)
        )
        binding.homeTab.setTabLayoutTitles(tabList!!)
    }

}