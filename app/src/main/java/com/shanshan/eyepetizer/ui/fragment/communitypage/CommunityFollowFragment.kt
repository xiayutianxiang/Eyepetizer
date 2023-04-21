package com.shanshan.eyepetizer.ui.fragment.communitypage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.home.community.FollowAdapter
import com.shanshan.eyepetizer.base.BaseFragment
import com.shanshan.eyepetizer.databinding.FragmentCommBinding
import com.shanshan.eyepetizer.ui.view.AutoPlayListener
import com.shanshan.eyepetizer.viewmodel.CommunityCommendViewModel
import com.shuyu.gsyvideoplayer.GSYVideoManager
import kotlinx.coroutines.launch

class CommunityFollowFragment : BaseFragment<FragmentCommBinding, CommunityCommendViewModel>() {

    private val followAdapter by lazy {
        FollowAdapter()
    }

    companion object {
        fun newInstance() = CommunityFollowFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.recyclerView.adapter = followAdapter
        binding.recyclerView.addOnScrollListener(AutoPlayListener(activity,followAdapter))
        lifecycleScope.launch {
            viewModel.getCommunityFollowContent().collect {
                followAdapter.submitData(it)
            }
        }

        addLoadStateListener()
    }

    private fun addLoadStateListener() {
        followAdapter.addLoadStateListener {
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

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        GSYVideoManager.releaseAllVideos()
        binding.recyclerView.clearOnScrollListeners()
        super.onDestroy()
    }
}