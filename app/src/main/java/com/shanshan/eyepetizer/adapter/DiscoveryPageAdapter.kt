package com.shanshan.eyepetizer.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.banner.BannerViewAdapter
import com.shanshan.eyepetizer.adapter.discovery.ColumnItemCollectionAdapter
import com.shanshan.eyepetizer.adapter.holder.*
import com.shanshan.eyepetizer.data.DiscoveryData
import com.shanshan.eyepetizer.data.ItemX
import com.shanshan.eyepetizer.databinding.ItemSpecialTypeBriefCardBinding
import com.shanshan.eyepetizer.ui.extension.load
import com.shanshan.eyepetizer.ui.fragment.homepage.DiscoveryFragment
import com.shanshan.eyepetizer.utils.LogUtils
import com.shanshan.eyepetizer.utils.ResourceUtils
import com.shanshan.eyepetizer.utils.dp2px
import com.zhpan.bannerview.constants.PageStyle

class DiscoveryPageAdapter(
    val fragment: DiscoveryFragment,
    private val dataList: DiscoveryData
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TAG = "DiscoveryPageAdapter"
    }

    override fun getItemViewType(position: Int): Int {
        return RecyclerViewUtil.getInnerItemViewType(dataList.itemList[position])
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

            is SpecialTextHeader5ViewHolder -> {
                //设置标题
                holder.tvTitle.text = item.data.text
            }

            is SpecialSquareCardCollectionViewHolder -> {
                holder.binding.tvTitle.text = item.data.header.title
                holder.binding.tvRightText.text = item.data.header.rightText

                holder.binding.recyclerView.setHasFixedSize(true)
                holder.binding.recyclerView.isNestedScrollingEnabled = true
                holder.binding.recyclerView.layoutManager =
                    GridLayoutManager(holder.binding.root.context, 2).apply {
                        orientation = GridLayoutManager.HORIZONTAL
                    }
                if (holder.binding.recyclerView.itemDecorationCount == 0) {
                    holder.binding.recyclerView.addItemDecoration(
                        SpecialSquareCardCollectionItemDecoration()
                    )
                }

                val dataList = item.data.itemList.filter {
                    it.type == "squareCardOfCategory" && it.data.dataType == "SquareCard"
                }

                holder.binding.recyclerView.adapter = SpecialSquareCardCollectionAdapter(dataList)
            }

            is SpecialCardTypeBriefCardHolder -> {
                LogUtils.d(TAG, "text ---> ${item.data.title}")
                holder.tvTitle.text = item.data.title
                holder.ivPicture.load(item.data.icon)
            }

            is SpecialColumnCardListHolder -> {
                holder.tvTitle.text = item.data.header.title
                holder.tvRightText.text = item.data.header.rightText

                holder.columnRecyclerView.layoutManager =
                    GridLayoutManager(holder.itemView.context, 2)
                holder.columnRecyclerView.adapter = ColumnItemCollectionAdapter(item.data.itemList)
            }

            is SpecialTextHeader7ViewHolder -> {
                holder.tvTitle.text = item.data.text
                holder.tvRightText.text = item.data.rightText
            }

            is SpecialTextHeader8ViewHolder -> {
                LogUtils.d(TAG,"item ---> ${item.data}")
                holder.binding.tvTitle8.text = item.data.text
                holder.binding.tvRightText8.text = item.data.rightText
            }

            is SpecialTagBriefCardViewHolder -> {
                Glide.with(holder.itemView.context).load(item.data.icon).into(holder.ivPicture)
                holder.tvTitle.text = item.data.title
                holder.tvDescription.text = item.data.description
            }

            is SpecialVideoSmallCardViewHolder -> {
                holder.binding.tvTitle.text = item.data.title
                holder.binding.tvDescription.text = item.data.description
                holder.binding.ivPicture.load(item.data.cover.feed, 4f)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.itemList.size
    }

    inner class SpecialSquareCardCollectionAdapter(private val dataList: List<ItemX>) :
        RecyclerView.Adapter<SpecialSquareCardCollectionAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                ItemSpecialTypeBriefCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = dataList[position]
            holder.binding.tvTitle.text = data.data.title
            holder.binding.ivPicture.load(data.data.image, 4f)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        inner class ViewHolder(val binding: ItemSpecialTypeBriefCardBinding) :
            RecyclerView.ViewHolder(binding.root)
    }


    class SpecialSquareCardCollectionItemDecoration : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view) // item position
            val count = parent.adapter?.itemCount //item count
            val spanIndex = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex
            val spanCount = 2
            val lastRowFirstItemPostion = count?.minus(spanCount)   //最后一行,第一个item索引
            val space = dp2px(1f)
            val rightCountSpace = dp2px(14f)

            when (spanIndex) {
                0 -> {
                    outRect.bottom = space
                }
                spanCount - 1 -> {
                    outRect.top = space
                }
                else -> {
                    outRect.top = space
                    outRect.bottom = space
                }
            }
            when {
                position < spanCount -> {
                    outRect.right = space
                }
                position < lastRowFirstItemPostion!! -> {
                    outRect.left = space
                    outRect.right = space
                }
                else -> {
                    outRect.left = space
                    outRect.right = rightCountSpace
                }
            }
        }
    }
}