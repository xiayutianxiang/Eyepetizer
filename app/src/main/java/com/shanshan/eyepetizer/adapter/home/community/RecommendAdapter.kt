package com.shanshan.eyepetizer.adapter.home.community

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shanshan.eyepetizer.adapter.holder.RecyclerViewUtil
import com.shanshan.eyepetizer.adapter.holder.SpecialHorizontalScrollCardItemCollectionViewHolder
import com.shanshan.eyepetizer.data.CommunityRecommendData
import com.shanshan.eyepetizer.utils.LogUtils

class RecommendAdapter :
    PagingDataAdapter<CommunityRecommendData.Item, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {

        private const val TAG = "RecommendAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CommunityRecommendData.Item>() {
            override fun areItemsTheSame(
                oldItem: CommunityRecommendData.Item,
                newItem: CommunityRecommendData.Item
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CommunityRecommendData.Item,
                newItem: CommunityRecommendData.Item
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return RecyclerViewUtil.getInnerItemViewType(getItem(position)!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is SpecialHorizontalScrollCardItemCollectionViewHolder -> {
                
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerViewUtil.getItemViewHolder(parent, viewType)
    }
}