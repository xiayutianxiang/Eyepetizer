package com.shanshan.eyepetizer.ui.fragment.communitypage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.home.community.RecommendAdapter
import com.shanshan.eyepetizer.base.BaseFragment
import com.shanshan.eyepetizer.databinding.FragmentCommBinding
import com.shanshan.eyepetizer.viewmodel.CommunityCommendViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommunityRecommendFragment : BaseFragment<FragmentCommBinding, CommunityCommendViewModel>() {

    private lateinit var communityRecommendAdapter: RecommendAdapter

    companion object {
        fun newInstance() = CommunityRecommendFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        communityRecommendAdapter = RecommendAdapter()
        //加载社区推荐数据
        lifecycleScope.launch {
            viewModel.getCommunityRecommendContent().collectLatest {
                communityRecommendAdapter.submitData(it)
            }
        }

        binding.refresh.setOnRefreshListener {
            communityRecommendAdapter.refresh()
        }
        addLoadStateListener()
    }

    private fun addLoadStateListener() {
        communityRecommendAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> { //没有加载中 --> 加载数据前和加载数据后调用
                    //隐藏progressbar并结束刷新
                    binding.loading.loading.visibility = View.GONE
                    binding.refresh.finishRefresh()

                    //如果第一页数据就没有更多了，第一页不会触发append
                    if (it.source.append.endOfPaginationReached) {
                        binding.refresh.setEnableLoadMore(true)
                        binding.refresh.finishLoadMoreWithNoMoreData()
                    } else {
                        binding.refresh.setEnableLoadMore(false)
                    }
                }

                is LoadState.Loading -> {  //加载中 --> 加载数据时调用
                    if (binding.refresh.state != RefreshState.Refreshing) {
                        binding.loading.loading.visibility = View.VISIBLE
                    }
                }

                is LoadState.Error -> { //加载失败时调用

                }
            }
        }
    }

    override fun initData() {

    }

    override fun initObserver() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_comm
    }
}