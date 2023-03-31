package com.shanshan.eyepetizer.adapter.home.community

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.holder.RecyclerViewUtil
import com.shanshan.eyepetizer.adapter.holder.SpecialHorizontalScrollCardItemCollectionViewHolder
import com.shanshan.eyepetizer.data.CommunityRecommendData
import com.shanshan.eyepetizer.ui.extension.load
import com.shanshan.eyepetizer.ui.fragment.communitypage.CommunityRecommendFragment
import com.shanshan.eyepetizer.ui.view.TypefaceTextView

class RecommendAdapter(val fragment: CommunityRecommendFragment) :
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
        val item = getItem(position)!!
        when (holder) {
            is SpecialHorizontalScrollCardItemCollectionViewHolder -> {
                holder.binding.recyclerView.layoutManager =
                    LinearLayoutManager(fragment.context).apply {
                        orientation = LinearLayoutManager.HORIZONTAL
                    }
                holder.binding.recyclerView.addItemDecoration(
                    SquareCardOfCommunityContentItemDecoration(fragment)
                )
                holder.binding.recyclerView.adapter =
                    HorizontalScrollCardItemCollectionAdapter(item.data.itemList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerViewUtil.getItemViewHolder(parent, viewType)
    }

    //item间距
    class SquareCardOfCommunityContentItemDecoration(val fragment: CommunityRecommendFragment) :
        RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view) // item position
            val count = parent.adapter?.itemCount?.minus(1) //item count

            when (position) {
                0 -> {
                    /*outRect.left = fragment.bothSideSpace*/
                    outRect.right = fragment.middleSpace
                }
                count -> {
                    outRect.left = fragment.middleSpace
                    /*outRect.right = fragment.bothSideSpace*/
                }
                else -> {
                    outRect.left = fragment.middleSpace
                    outRect.right = fragment.middleSpace
                }
            }
        }
    }

    //主题创作、话题讨论的adapter
    class HorizontalScrollCardItemCollectionAdapter(var itemList: List<CommunityRecommendData.Item.Data.Item>) :
        RecyclerView.Adapter<HorizontalScrollCardItemCollectionAdapter.InnerHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): HorizontalScrollCardItemCollectionAdapter.InnerHolder {
            return InnerHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_community_horizontal_scroll_card_itemcollection_item_type,
                    parent, false
                )
            )
        }

        inner class InnerHolder(view: View) : RecyclerView.ViewHolder(view) {
            val ivBgPicture: AppCompatImageView = view.findViewById(R.id.ivBgPicture)
            val tvTitle: TypefaceTextView = view.findViewById(R.id.tvTitle)
            val tvSubTitle: TypefaceTextView = view.findViewById(R.id.tvSubTitle)
        }

        override fun onBindViewHolder(
            holder: HorizontalScrollCardItemCollectionAdapter.InnerHolder,
            position: Int
        ) {
            val item = itemList[position]
            holder.ivBgPicture.load(item.data.bgPicture)
            holder.tvTitle.text = item.data.title
            holder.tvSubTitle.text = item.data.subTitle
        }

        override fun getItemCount(): Int {
            return itemList.size
        }
    }
}