package com.shanshan.eyepetizer.ui.fragment.notifypage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.home.notice.PushAdapter
import com.shanshan.eyepetizer.base.BaseFragment
import com.shanshan.eyepetizer.databinding.FragmentCommBinding
import com.shanshan.eyepetizer.ui.extension.gone
import com.shanshan.eyepetizer.viewmodel.NoticeViewModel
import kotlinx.coroutines.launch

class PushFragment : BaseFragment<FragmentCommBinding, NoticeViewModel>() {

    private val adapter by lazy {
        PushAdapter()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PushFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager

        addLoadStateListener()

        lifecycleScope.launch {
            viewModel.getNoticePushContent().collect {
                adapter.submitData(it)
            }
        }
    }

    /**
     * 添加状态监听
     */
    private fun addLoadStateListener() {
        adapter.addLoadStateListener {
            when (it.refresh) {
                //不是加载中
                is LoadState.NotLoading -> {
                    //隐藏progressbar，结束刷新
                    binding.loading.loading.gone()
                    binding.refresh.finishRefresh()

                    //如果第一页数据就没有更多了，第一页不触发append
                    if (it.source.append.endOfPaginationReached) {
                        binding.refresh.setEnableLoadMore(true)
                        binding.refresh.finishLoadMoreWithNoMoreData()
                    } else {
                        binding.refresh.setEnableLoadMore(false)
                    }
                }
                //是加载中(加载数据时调用)
                is LoadState.Loading -> {
                    if (binding.refresh.state != RefreshState.Refreshing){
                        binding.loading.loading.visibility = View.VISIBLE
                    }
                }

                //加载失败
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