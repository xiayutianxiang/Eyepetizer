package com.shanshan.eyepetizer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.banner.BannerViewAdapter
import com.shanshan.eyepetizer.adapter.holder.DiscoveryItemViewType
import com.shanshan.eyepetizer.adapter.holder.HorizontalScrollCardHolder
import com.shanshan.eyepetizer.adapter.holder.RecyclerViewUtil
import com.shanshan.eyepetizer.adapter.holder.SpecialSquareCardCollectionViewHolder
import com.shanshan.eyepetizer.data.DiscoveryData
import com.shanshan.eyepetizer.data.ItemX
import com.shanshan.eyepetizer.ui.fragment.homepage.DiscoveryFragment
import com.shanshan.eyepetizer.utils.LogUtils
import com.shanshan.eyepetizer.utils.ResourceUtils
import com.shanshan.eyepetizer.utils.dp2px
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.PageStyle
import kotlin.properties.ReadOnlyProperty

class DiscoveryPageAdapter(
    val fragment: DiscoveryFragment,
    private val dataList: DiscoveryData
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val TAG = "DiscoveryPageAdapter"
    }

    override fun getItemViewType(position: Int): Int {
        LogUtils.d(TAG, "position ---> $position" +
                "")
        return RecyclerViewUtil.getItemViewType(dataList.itemList[position])
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
        }
    }

    override fun getItemCount(): Int {
        LogUtils.d(this.toString(), "size = {${dataList.itemList.size}}")
        return dataList.itemList.size
    }
}