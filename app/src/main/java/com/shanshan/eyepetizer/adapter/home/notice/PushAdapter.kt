package com.shanshan.eyepetizer.adapter.home.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shanshan.eyepetizer.data.NoticePushData
import com.shanshan.eyepetizer.databinding.ItemNotificationPushBinding
import com.shanshan.eyepetizer.ui.extension.load
import com.shanshan.eyepetizer.utils.DateUtil

class PushAdapter :
    PagingDataAdapter<NoticePushData.Message, PushAdapter.PushViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NoticePushData.Message>() {
            override fun areItemsTheSame(
                oldItem: NoticePushData.Message,
                newItem: NoticePushData.Message
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NoticePushData.Message,
                newItem: NoticePushData.Message
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: PushViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.binding.tvTitle.text = item.title
        holder.binding.tvContent.text = item.content
        holder.binding.tvTime.text = DateUtil.getConvertedDate(item.date)
        holder.binding.ivAvatar.load(item.icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PushViewHolder {
        return PushViewHolder(
            ItemNotificationPushBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class PushViewHolder(var binding:ItemNotificationPushBinding) : RecyclerView.ViewHolder(binding.root)
}