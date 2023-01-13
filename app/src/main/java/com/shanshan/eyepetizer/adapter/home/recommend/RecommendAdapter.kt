package com.shanshan.eyepetizer.adapter.home.recommend

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shanshan.eyepetizer.adapter.holder.RecyclerViewUtil
import com.shanshan.eyepetizer.adapter.holder.SpecialFollowCardViewHolder
import com.shanshan.eyepetizer.adapter.holder.SpecialTextHeader5ViewHolder
import com.shanshan.eyepetizer.adapter.holder.SpecialVideoSmallCardViewHolder
import com.shanshan.eyepetizer.ui.extension.conversionVideoDuration
import com.shanshan.eyepetizer.ui.extension.load

class RecommendAdapter :
    PagingDataAdapter<HomeRecommendData.Item, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        const val TAG = "CommendAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HomeRecommendData.Item>() {

            override fun areItemsTheSame(
                oldItem: HomeRecommendData.Item,
                newItem: HomeRecommendData.Item
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: HomeRecommendData.Item,
                newItem: HomeRecommendData.Item
            ) = oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return RecyclerViewUtil.getInnerItemViewType(getItem(position)!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)!!

        when (holder) {
            is SpecialTextHeader5ViewHolder -> {
                holder.tvTitle.text = item.data.text
            }

            is SpecialFollowCardViewHolder -> {
                holder.binding.ivAvatar.load(item.data.header.icon)
                holder.binding.tvDescription.text = item.data.header.description
                holder.binding.ivVideo.load(item.data.content.data.cover.feed, 4f)
                holder.binding.tvVideoDuration.text =
                    item.data.content.data.duration.conversionVideoDuration()
                holder.binding.tvTitle.text = item.data.header.title
            }

            is SpecialVideoSmallCardViewHolder -> {
                holder.binding.ivPicture.load(item.data.cover.feed, 4f)
                holder.binding.tvDescription.text =
                    if (item.data.library == "DAILY") "#${item.data.category} /开眼精选" else "#${item.data.category}"
                holder.binding.tvTitle.text = item.data.title
                holder.binding.tvVideoDuration.text = item.data.duration.conversionVideoDuration()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerViewUtil.getItemViewHolder(parent, viewType)
    }
}