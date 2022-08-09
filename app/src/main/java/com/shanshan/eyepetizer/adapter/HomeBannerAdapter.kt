package com.shanshan.eyepetizer.adapter

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.data.Data
import com.shanshan.eyepetizer.data.ItemX
import com.shanshan.eyepetizer.utils.LogUtils
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class HomeBannerAdapter : BaseBannerAdapter<ItemX>() {

    private var bannerData: Data? = null
    private var mContext:Context?=null
    override fun bindData(
        holder: BaseViewHolder<ItemX>?,
        data: ItemX?,
        position: Int,
        pageSize: Int
    ) {
        Glide.with(mContext!!).load(data?.data?.image)
            .into((holder!!.findViewById(R.id.item_banner_image) as AppCompatImageView))
        LogUtils.d(this.toString(), data?.data?.image)
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.home_banner_view
    }

    @JvmName("setBannerData1")
    fun setBannerData(homeBannerData: Data, context: Context?) {
        mContext = context
        bannerData = homeBannerData
    }
}