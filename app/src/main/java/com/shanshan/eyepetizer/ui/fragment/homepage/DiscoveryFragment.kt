package com.shanshan.eyepetizer.ui.fragment.homepage

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.DiscoveryPageAdapter
import com.shanshan.eyepetizer.base.BaseFragment
import com.shanshan.eyepetizer.databinding.FragmentDicoveryBinding
import com.shanshan.eyepetizer.viewmodel.DiscoveryViewModel

class DiscoveryFragment : BaseFragment<FragmentDicoveryBinding, DiscoveryViewModel>() {

    private lateinit var adapter: DiscoveryPageAdapter

    companion object {
        const val TAG = "DiscoveryFragment"

        fun newInstance() = DiscoveryFragment()
    }

    override fun initView() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_dicovery
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
                binding.homeRv.layoutManager = LinearLayoutManager(context)
                binding.homeRv.adapter = adapter
                binding.homeRv.setHasFixedSize(true)
                binding.homeRv.itemAnimator = null
            }
        })
    }
}