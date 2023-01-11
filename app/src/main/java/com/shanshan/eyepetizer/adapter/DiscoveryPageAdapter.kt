package com.shanshan.eyepetizer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.banner.BannerViewAdapter
import com.shanshan.eyepetizer.adapter.discovery.ColumnItemCollectionAdapter
import com.shanshan.eyepetizer.adapter.holder.*
import com.shanshan.eyepetizer.data.DiscoveryData
import com.shanshan.eyepetizer.ui.extension.load
import com.shanshan.eyepetizer.ui.fragment.homepage.DiscoveryFragment
import com.shanshan.eyepetizer.utils.LogUtils
import com.shanshan.eyepetizer.utils.ResourceUtils
import com.shanshan.eyepetizer.utils.dp2px
import com.zhpan.bannerview.constants.PageStyle

class DiscoveryPageAdapter(
    val fragment: DiscoveryFragment,
    private val dataList: DiscoveryData
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TAG = "DiscoveryPageAdapter"
    }

    override fun getItemViewType(position: Int): Int {
        return RecyclerViewUtil.getInnerItemViewType(dataList.itemList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        LogUtils.d(this.toString(), "viewType ---> $viewType")
        return RecyclerViewUtil.getItemViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList.itemList[position]
        when (holder) {
            //Banner类型
            is HorizontalScrollCardHolder -> {
                holder.bannerViewPager.run {
                    setCanLoop(true)
                    setRoundCorner(dp2px(4f))
                    setPageMargin(8)
                    setPageStyle(PageStyle.MULTI_PAGE_SCALE)
                    adapter = BannerViewAdapter()
                    setIndicatorVisibility(View.GONE)
                    setRevealWidth(ResourceUtils.getDimen(R.dimen.listSpaceSize))
                    removeDefaultPageTransformer()
                    create(item.data.itemList)
                }
            }

            is SpecialTextHeader5ViewHolder -> {
                //设置标题
                holder.tvTitle.text = item.data.text
            }

            is SpecialCardTypeBriefCardHolder -> {
                LogUtils.d(TAG, "text ---> ${item.data.title}")
                holder.tvTitle.text = item.data.title
                holder.ivPicture.load(item.data.icon)
            }

            is SpecialColumnCardListHolder -> {
                holder.tvTitle.text = item.data.header.title
                holder.columnRecyclerView.layoutManager =
                    GridLayoutManager(holder.itemView.context, 2)
                holder.columnRecyclerView.adapter = ColumnItemCollectionAdapter(item.data.itemList)
            }

            is SpecialTextHeader7ViewHolder -> {
                holder.tvTitle.text = item.data.text
                holder.tvRightText.text = item.data.rightText
            }

            is SpecialTagBriefCardViewHolder -> {
                Glide.with(holder.itemView.context).load(item.data.icon).into(holder.ivPicture)
                holder.tvTitle.text = item.data.title
                holder.tvDescription.text = item.data.description
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.itemList.size
    }
}