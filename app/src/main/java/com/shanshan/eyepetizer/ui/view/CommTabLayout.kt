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

class CommTabLayout : RelativeLayout {

    private var mSelectedClick: OnTabItemSelectedClick? = null
    private lateinit var binding: TabLayoutHomeBinding

    constructor(context: Context?) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        binding = TabLayoutHomeBinding.inflate(LayoutInflater.from(context), this, true)
        binding.tabLayout.isTabIndicatorFullWidth = false
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
    fun setTabLayoutTitles(titles : ArrayList<String>) {
        for (i in titles.indices) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(titles[i]))
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