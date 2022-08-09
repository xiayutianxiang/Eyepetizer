package com.shanshan.eyepetizer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.banner.BannerViewAdapter
import com.shanshan.eyepetizer.data.DiscoveryData
import com.shanshan.eyepetizer.data.ItemX
import com.shanshan.eyepetizer.ui.fragment.homepage.DiscoveryFragment
import com.shanshan.eyepetizer.utils.LogUtils
import com.shanshan.eyepetizer.utils.ResourceUtils
import com.shanshan.eyepetizer.utils.dp2px
import com.zhpan.bannerview.BannerViewPager

class DiscoveryPageAdapter(
    val fragment: DiscoveryFragment,
    val dataList: DiscoveryData
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val BANNER_TYPE: Int = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.home_banner_view,
            parent,
            false
        )
        return DiscoveryBannerHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList.itemList[position]

        when (holder) {
            is DiscoveryBannerHolder -> {
                holder.bannerViewPager.run {
                    setRoundCorner(dp2px(4f))
                    setPageMargin(8)
                    adapter = BannerViewAdapter()
                    setIndicatorVisibility(View.GONE)
                    setRevealWidth(ResourceUtils.getDimen(R.dimen.listSpaceSize))
                    removeDefaultPageTransformer()
                    create(item.data.itemList)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        LogUtils.d(this.toString(), "size = {${dataList.itemList.size}}")
        return dataList.itemList.size
    }

    override fun getItemViewType(position: Int): Int {

        if (position == 0) {
            return BANNER_TYPE
        }

        return super.getItemViewType(position)
    }

    inner class DiscoveryBannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val bannerViewPager: BannerViewPager<ItemX> = itemView.findViewById(R.id.banner_view)
    }
}