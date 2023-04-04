package com.shanshan.eyepetizer.ui.fragment.communitypage

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.home.community.RecommendAdapter
import com.shanshan.eyepetizer.base.BaseFragment
import com.shanshan.eyepetizer.databinding.FragmentCommBinding
import com.shanshan.eyepetizer.utils.ResourceUtils
import com.shanshan.eyepetizer.utils.dp2px
import com.shanshan.eyepetizer.viewmodel.CommunityCommendViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommunityRecommendFragment : BaseFragment<FragmentCommBinding, CommunityCommendViewModel>() {

    private lateinit var communityRecommendAdapter: RecommendAdapter

    /**
     * 列表左or右间距
     */
    val bothSideSpace = ResourceUtils.getDimen(R.dimen.listSpaceSize)

    /**
     * 列表中间内间距，左or右。
     */
    val middleSpace = dp2px(3f)

    companion object {
        fun newInstance() = CommunityRecommendFragment()
    }

    /**
     * 通过获取屏幕宽度来计算出每张图片最大的宽度。
     *
     * @return 计算后得出的每张图片最大的宽度。
     */
    val maxImageWidth: Int
        get() {
            val windowManager = activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay?.getMetrics(metrics)
            val columnWidth = metrics.widthPixels
            return (columnWidth - ((bothSideSpace * 2) + (middleSpace * 2))) / 2
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        communityRecommendAdapter = RecommendAdapter(this)

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        ).apply {
            gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        }

        binding.recyclerView.apply {
            adapter = communityRecommendAdapter
            addItemDecoration(RecommendAdapter.ItemDecoration(this@CommunityRecommendFragment))
            setHasFixedSize(true)
            itemAnimator = null
        }

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