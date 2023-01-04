package com.shanshan.eyepetizer.ui.fragment.homepage

import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.base.BaseFragment
import com.shanshan.eyepetizer.databinding.FragmentRecommendBinding
import com.shanshan.eyepetizer.model.HomeRecommendViewModel
import com.shanshan.eyepetizer.utils.LogUtils
import kotlinx.coroutines.launch

class RecommendFragment : BaseFragment<FragmentRecommendBinding, HomeRecommendViewModel>() {

    companion object {
        private const val TAG = "RecommendFragment"
        fun newInstance() = RecommendFragment()
    }

    override fun initView() {

    }

    override fun initData() {
        LogUtils.d(TAG,"start load data")
        lifecycleScope.launch {
            viewModel.getPagingData().collect {
                it.map {
                    LogUtils.d(TAG,"item ---> $it")
                }
            }
        }
    }

    override fun initObserver() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_recommend
    }
}