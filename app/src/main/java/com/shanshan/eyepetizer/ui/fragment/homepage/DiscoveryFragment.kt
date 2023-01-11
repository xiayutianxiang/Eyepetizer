package com.shanshan.eyepetizer.ui.fragment.homepage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.DiscoveryPageAdapter
import com.shanshan.eyepetizer.base.BaseFragment
import com.shanshan.eyepetizer.databinding.FragmentCommBinding
import com.shanshan.eyepetizer.utils.LogUtils
import com.shanshan.eyepetizer.viewmodel.DiscoveryViewModel

class DiscoveryFragment : BaseFragment<FragmentCommBinding, DiscoveryViewModel>(),
    OnRefreshListener {

    private lateinit var adapter: DiscoveryPageAdapter

    companion object {
        const val TAG = "DiscoveryFragment"

        fun newInstance() = DiscoveryFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.refresh.setOnRefreshListener(this)
        binding.refresh.setEnableAutoLoadMore(false);//使上拉加载具有弹性效果
        binding.refresh.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
        binding.refresh.setEnableOverScrollBounce(false);//关闭越界回弹功能
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_comm
    }

    /**
     * 加载发现页数据
     */
    override fun initData() {
        viewModel.getDiscoveryData()
    }

    override fun initObserver() {
        viewModel.discoveryData.observe(this, Observer {
            if (it != null) {
                adapter = DiscoveryPageAdapter(this, it)
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.setHasFixedSize(true)
                binding.recyclerView.itemAnimator = null
                binding.loading.loading.visibility = View.GONE
                LogUtils.d(TAG,"data --- > $it")
            }
        })
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        binding.refresh.finishLoadMore()
        binding.refresh.setNoMoreData(true)
        LogUtils.d(TAG,"state --- > ${binding.refresh.state}")
    }
}