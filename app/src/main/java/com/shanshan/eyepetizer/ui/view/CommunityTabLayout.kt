package com.shanshan.eyepetizer.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.databinding.TabLayoutCommunityBinding
import com.shanshan.eyepetizer.databinding.TabLayoutHomeBinding
import com.shanshan.eyepetizer.utils.ResourceUtils

class CommunityTabLayout : RelativeLayout {

    private var mSelectedClick: OnTabItemSelectedClick? = null
    private lateinit var binding: TabLayoutCommunityBinding

    private var mTitles = arrayOf(
        ResourceUtils.getString(R.string.community_recommend),
        ResourceUtils.getString(R.string.community_follow)
    )

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initView()
    }

    private fun initView() {
        binding = TabLayoutCommunityBinding.inflate(LayoutInflater.from(context), this, true)

        initTabLayout()
        initListener()
    }

    private fun initListener() {

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    mSelectedClick?.tabItemSelected(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.tabLayout.setOnLongClickListener { return@setOnLongClickListener true }
    }

    /**
     * 初始化标签页
     */
    private fun initTabLayout() {
        for (i in mTitles.indices) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(mTitles[i]))
        }
    }

    fun setCurSelected(position: Int) {
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
    }

    interface OnTabItemSelectedClick {
        fun tabItemSelected(position: Int)
    }

    fun setOnTabItemSelectedClick(selectedClick: OnTabItemSelectedClick) {
        this.mSelectedClick = selectedClick
    }
}