package com.shanshan.eyepetizer.adapter.banner

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.data.Item
import com.shanshan.eyepetizer.data.ItemX
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class BannerViewAdapter : BaseBannerAdapter<ItemX>() {
    override fun bindData(
        holder: BaseViewHolder<ItemX>,
        data: ItemX?,
        position: Int,
        pageSize: Int
    ) {
        val imageView:ImageView = holder.findViewById(R.id.item_banner_image)

        Glide.with(holder.itemView.context).load(data?.data?.image).into(imageView)
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner
    }
}