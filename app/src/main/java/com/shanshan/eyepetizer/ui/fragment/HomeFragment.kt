package com.shanshan.eyepetizer.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.HomeViewPageAdapter
import com.shanshan.eyepetizer.databinding.FragmentHomeBinding
import com.shanshan.eyepetizer.ui.fragment.homepage.DailyFragment
import com.shanshan.eyepetizer.ui.fragment.homepage.FindFragment
import com.shanshan.eyepetizer.ui.fragment.homepage.RecommendFragment
import com.shanshan.eyepetizer.ui.view.HomeTabLayout

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var fragments = ArrayList<Fragment>()

    private var homeAdapter: HomeViewPageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initFragment()
        initViewPager()
        return binding.root
    }

    private fun initFragment() {
        fragments.add(FindFragment())
        fragments.add(RecommendFragment())
        fragments.add(DailyFragment())
    }

    private fun initViewPager() {
        homeAdapter = fragmentManager?.let { HomeViewPageAdapter(it, lifecycle, fragments) }
        binding.homePage.adapter = homeAdapter

        //绑定ViewPage2
        binding.homePage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.homeTab.setCurSelected(position)
            }
        })
        //设置HomeTabLayout的点击
        binding.homeTab.setOnTabItemSelectedClick(object : HomeTabLayout.OnTabItemSelectedClick {
            override fun tabItemSelected(position: Int) {
                binding.homePage.setCurrentItem(position, true)
            }
        })
    }

    companion object {

    }
}