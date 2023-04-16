package com.shanshan.eyepetizer.adapter.home.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.data.CommunityFollowData
import com.shanshan.eyepetizer.databinding.ItemCommunityAutoPlayFollowCardFollowCardTypeBinding
import com.shanshan.eyepetizer.ui.extension.load

class FollowAdapter :
    PagingDataAdapter<CommunityFollowData.Item, FollowAdapter.FollowViewHolder>(DIFF_CALLBACK) {

    companion object {

        const val AUTO_PLAY_FOLLOW_CARD = "autoPlayFollowCard"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CommunityFollowData.Item>() {
            override fun areItemsTheSame(
                oldItem: CommunityFollowData.Item,
                newItem: CommunityFollowData.Item
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CommunityFollowData.Item,
                newItem: CommunityFollowData.Item
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.binding.ivAvatar.load(item.data.header.icon)
        holder.binding.tvNickname.text = item.data.content.data.author.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        return FollowViewHolder(
            ItemCommunityAutoPlayFollowCardFollowCardTypeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class FollowViewHolder(val binding: ItemCommunityAutoPlayFollowCardFollowCardTypeBinding) :
        RecyclerView.ViewHolder(binding.root)
}