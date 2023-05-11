package com.shanshan.eyepetizer.adapter.home.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shanshan.eyepetizer.data.CommunityFollowData
import com.shanshan.eyepetizer.databinding.ItemCommunityAutoPlayFollowCardFollowCardTypeBinding
import com.shanshan.eyepetizer.ui.extension.conversionVideoDuration
import com.shanshan.eyepetizer.ui.extension.gone
import com.shanshan.eyepetizer.ui.extension.load
import com.shanshan.eyepetizer.ui.extension.visible
import com.shanshan.eyepetizer.utils.AutoPlayUtil
import com.shanshan.eyepetizer.utils.DateUtil
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack

class FollowAdapter :
    PagingDataAdapter<CommunityFollowData.Item, FollowAdapter.FollowViewHolder>(DIFF_CALLBACK) {

    companion object {

        const val TAG = "FollowAdapter"

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
        item.data.content.data.apply {
            holder.binding.tvNickname.text = author.name
            holder.binding.tvReleaseTime.text = DateUtil.getDate(releaseTime, "HH:mm")
            holder.binding.tvTitle.text = title
            holder.binding.tvContent.text = description
            holder.binding.tvCollectionCount.text = consumption.collectionCount.toString()
            holder.binding.tvReplyCount.text = consumption.replyCount.toString()
            holder.binding.tvVideoDuration.text = duration.conversionVideoDuration()
            holder.binding.tvVideoDuration.visible()    //开始播放后，隐藏tvVideoDuration
            AutoPlayUtil.autoPlay(
                holder.binding.videoPlayer, holder.itemView.context,
                TAG, playUrl, position, cover.feed, GSYFollowCallBack(holder)
            )
        }
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

    inner class GSYFollowCallBack(private val holder: FollowViewHolder) : GSYSampleCallBack() {
        override fun onPrepared(url: String?, vararg objects: Any?) {
            super.onPrepared(url, *objects)
            holder.binding.tvVideoDuration.gone()
            //是否需要静音
            GSYVideoManager.instance().isNeedMute = true
        }

        override fun onClickResume(url: String?, vararg objects: Any?) {
            super.onClickResume(url, *objects)
            holder.binding.tvVideoDuration.gone()
        }

        override fun onClickBlank(url: String?, vararg objects: Any?) {
            super.onClickBlank(url, *objects)
            holder.binding.tvVideoDuration.visible()
        }
    }
}