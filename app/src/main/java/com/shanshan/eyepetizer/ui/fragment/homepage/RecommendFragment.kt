package com.shanshan.eyepetizer.ui.fragment.homepage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.home.recommend.RecommendAdapter
import com.shanshan.eyepetizer.base.BaseFragment
import com.shanshan.eyepetizer.databinding.FragmentRecommendBinding
import com.shanshan.eyepetizer.viewmodel.HomeRecommendViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecommendFragment : BaseFragment<FragmentRecommendBinding, HomeRecommendViewModel>() {

    private lateinit var recommendAdapter: RecommendAdapter

    companion object {
        private const val TAG = "RecommendFragment"
        fun newInstance() = RecommendFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recommendAdapter = RecommendAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = recommendAdapter

        lifecycleScope.launch {
            viewModel.getPagingData().collectLatest {
                recommendAdapter.submitData(it)
            }
        }

    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initObserver() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_recommend
    }
}