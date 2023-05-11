package com.shanshan.eyepetizer.ui.fragment

import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.ui.fragment.notifypage.InteractiveFragment
import com.shanshan.eyepetizer.ui.fragment.notifypage.LetterFragment
import com.shanshan.eyepetizer.ui.fragment.notifypage.PushFragment
import com.shanshan.eyepetizer.utils.ResourceUtils

class NoticeFragment : BaseFragmentWithViewPager() {

    private var tabList: ArrayList<String>? = null

    override fun initFragments() {
        fragments.add(PushFragment.newInstance())
        fragments.add(InteractiveFragment.newInstance())
        fragments.add(LetterFragment.newInstance())
    }

    override fun initTabLists() {
        tabList = arrayListOf(
            ResourceUtils.getString(R.string.notice_push),
            ResourceUtils.getString(R.string.notice_interaction),
            ResourceUtils.getString(R.string.notice_private_messages)
        )
        binding.homeTab.setTabLayoutTitles(tabList!!)
    }
}