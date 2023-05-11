package com.shanshan.eyepetizer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.HomeViewPageAdapter
import com.shanshan.eyepetizer.databinding.FragmentHomeBinding
import com.shanshan.eyepetizer.ui.view.CommTabLayout

abstract class BaseFragmentWithViewPager : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    protected val binding get() = _binding!!

    protected val fragments = ArrayList<Fragment>()
    private var homeAdapter: HomeViewPageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        initFragments()
        initTabLists()
        initViewPager()

        return binding.root
    }

    /**
     * 初始化fragment集合
     */
    abstract fun initFragments()

    /**
     * 初始化tab标签集合
     */
    abstract fun initTabLists()

    /**
     * 初始化ViewPager
     */
    open fun initViewPager() {
        homeAdapter = HomeViewPageAdapter(requireActivity(), fragments)

        binding.homePage.adapter = homeAdapter

        //绑定ViewPage2
        binding.homePage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.homeTab.setCurSelected(position)
            }
        })

        //设置HomeTabLayout的点击
        binding.homeTab.setOnTabItemSelectedClick(object : CommTabLayout.OnTabItemSelectedClick {
            override fun tabItemSelected(position: Int) {
                binding.homePage.setCurrentItem(position, true)
            }
        })
    }

    fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

