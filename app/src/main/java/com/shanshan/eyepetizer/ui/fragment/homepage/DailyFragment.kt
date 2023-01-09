package com.shanshan.eyepetizer.ui.fragment.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.home.recommend.DailyAdapter
import com.shanshan.eyepetizer.base.BaseFragment
import com.shanshan.eyepetizer.databinding.FragmentDailyBinding
import com.shanshan.eyepetizer.viewmodel.HomeDailyViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DailyFragment : BaseFragment<FragmentDailyBinding, HomeDailyViewModel>() {

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
            adapter.refresh()
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
        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    binding.loading.loading.visibility = View.GONE
                }

                is LoadState.Loading -> {
                    if (!binding.refresh.isRefreshing) {
                        binding.loading.loading.visibility = View.VISIBLE
                    }
                }
                else -> {}
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
        return R.layout.fragment_daily
    }
}