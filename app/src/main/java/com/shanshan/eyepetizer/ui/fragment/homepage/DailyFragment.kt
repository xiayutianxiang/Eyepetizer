package com.shanshan.eyepetizer.ui.fragment.homepage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.home.recommend.DailyAdapter
import com.shanshan.eyepetizer.base.BaseFragment
import com.shanshan.eyepetizer.databinding.FragmentCommBinding
import com.shanshan.eyepetizer.viewmodel.HomeDailyViewModel
import kotlinx.coroutines.launch

class DailyFragment : BaseFragment<FragmentCommBinding, HomeDailyViewModel>() {

    private lateinit var adapter: DailyAdapter

    companion object {
        @JvmStatic
        fun newInstance() = DailyFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = DailyAdapter()

        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        //添加刷新
        binding.refresh.setOnRefreshListener {
            adapter.refresh()   //当控件下拉刷新时，调用adapter的刷新事件
            //adapter.retry() //失败重试
        }
        addLoadStateListener()

        lifecycleScope.launch {
            viewModel.getDailyPagingData().collect {
                adapter.submitData(it)
            }
        }
    }

    /**
     * 添加刷新事件
     */
    private fun addLoadStateListener() {
        //添加adapter的状态监听
        adapter.addLoadStateListener {
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

        adapter.addLoadStateListener {
            when (it.append) {  //向后加载更多
                is LoadState.NotLoading -> {
                    if (it.source.append.endOfPaginationReached) {
                        binding.refresh.setEnableLoadMore(true)
                        binding.refresh.finishLoadMoreWithNoMoreData()
                    } else {
                        binding.refresh.setEnableLoadMore(false)
                    }
                }
                is LoadState.Loading -> {

                }
                is LoadState.Error -> {

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