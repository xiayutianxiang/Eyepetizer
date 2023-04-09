package com.shanshan.eyepetizer.adapter.home.community

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.adapter.holder.EmptyViewHolder
import com.shanshan.eyepetizer.adapter.holder.ItemViewType
import com.shanshan.eyepetizer.data.CommunityRecommendData
import com.shanshan.eyepetizer.databinding.ItemCommunityColumnsCardFollowCardTypeBinding
import com.shanshan.eyepetizer.databinding.ItemCommunityHorizontalScrollcardItemCollectionTypeBinding
import com.shanshan.eyepetizer.databinding.ItemCommunityHorizontalScrollcardTypeBinding
import com.shanshan.eyepetizer.ui.extension.gone
import com.shanshan.eyepetizer.ui.extension.invisible
import com.shanshan.eyepetizer.ui.extension.load
import com.shanshan.eyepetizer.ui.extension.visible
import com.shanshan.eyepetizer.ui.fragment.communitypage.CommunityRecommendFragment
import com.shanshan.eyepetizer.ui.view.TypefaceTextView
import com.shanshan.eyepetizer.utils.LogUtils
import com.shanshan.eyepetizer.utils.ResourceUtils
import com.shanshan.eyepetizer.utils.dp2px
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import kotlin.time.Duration.Companion.days

class RecommendAdapter(val fragment: CommunityRecommendFragment) :
    PagingDataAdapter<CommunityRecommendData.Item, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {

        private const val TAG = "RecommendAdapter"
        const val STR_HORIZONTAL_SCROLLCARD_TYPE = "horizontalScrollCard"
        const val STR_COMMUNITY_COLUMNS_CARD = "communityColumnsCard"

        const val STR_HORIZONTAL_SCROLLCARD_DATA_TYPE = "HorizontalScrollCard"
        const val STR_ITEM_COLLECTION_DATA_TYPE = "ItemCollection"
        const val STR_FOLLOW_CARD_DATA_TYPE = "FollowCard"

        const val STR_VIDEO_TYPE = "video"
        const val STR_UGC_PICTURE_TYPE = "ugcPicture"

        const val HORIZONTAL_SCROLLCARD_ITEM_COLLECTION_TYPE =
            1   //type:horizontalScrollCard -> dataType:ItemCollection
        const val HORIZONTAL_SCROLLCARD_TYPE =
            2                   //type:horizontalScrollCard -> dataType:HorizontalScrollCard
        const val FOLLOW_CARD_TYPE =
            3                             //type:communityColumnsCard -> dataType:FollowCard

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
        val item = getItem(position)
        return when (item?.type) {
            STR_HORIZONTAL_SCROLLCARD_TYPE -> {
                when (item.data.dataType) {
                    STR_ITEM_COLLECTION_DATA_TYPE -> HORIZONTAL_SCROLLCARD_ITEM_COLLECTION_TYPE
                    STR_HORIZONTAL_SCROLLCARD_DATA_TYPE -> HORIZONTAL_SCROLLCARD_TYPE
                    else -> ItemViewType.UNKNOWN
                }
            }

            STR_COMMUNITY_COLUMNS_CARD -> {
                if (item.data.dataType == STR_FOLLOW_CARD_DATA_TYPE) FOLLOW_CARD_TYPE
                else ItemViewType.UNKNOWN
            }

            else -> ItemViewType.UNKNOWN
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HORIZONTAL_SCROLLCARD_ITEM_COLLECTION_TYPE -> {
                SpecialHorizontalScrollCardItemCollectionViewHolder(
                    ItemCommunityHorizontalScrollcardItemCollectionTypeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            HORIZONTAL_SCROLLCARD_TYPE -> {
                HorizontalScrollCardViewHolder(
                    ItemCommunityHorizontalScrollcardTypeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            FOLLOW_CARD_TYPE -> {
                FollowCardViewHolder(
                    ItemCommunityColumnsCardFollowCardTypeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> EmptyViewHolder(View(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)!!
        when (holder) {
            //主题、话题部分
            is SpecialHorizontalScrollCardItemCollectionViewHolder -> {
                (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan =
                    true
                val layoutManager = object : LinearLayoutManager(fragment.context) {
                    override fun canScrollHorizontally(): Boolean {
                        return false
                    }
                }
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                holder.binding.recyclerView.layoutManager = layoutManager

                if (holder.binding.recyclerView.itemDecorationCount == 0) {
                    holder.binding.recyclerView.addItemDecoration(
                        SquareCardOfCommunityContentItemDecoration(fragment)
                    )
                }
                holder.binding.recyclerView.adapter =
                    HorizontalScrollCardItemCollectionAdapter(fragment, item.data.itemList)
            }

            //轮播图
            is HorizontalScrollCardViewHolder -> {
                (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan =
                    true
                holder.binding.bannerViewPager.run {
                    setCanLoop(true)
                    setRoundCorner(dp2px(4f))
                    setAdapter(BannerViewPager())
                    setRevealWidth(0, ResourceUtils.getDimen(R.dimen.listSpaceSize))
                    if (item.data.itemList.size == 1) setPageMargin(0) else setPageMargin(dp2px(4f))
                    setIndicatorVisibility(View.GONE)
                    removeDefaultPageTransformer()
                    create(item.data.itemList)
                }
            }

            is FollowCardViewHolder -> {
                holder.binding.tvChoiceness.gone()
                holder.binding.ivPlay.gone()
                holder.binding.ivLayers.gone()

                if ((item.data.header.iconType ?: "".trim()) == "round") {
                    holder.binding.ivAvatar.invisible()
                    holder.binding.ivRoundAvatar.visible()
                    holder.binding.ivRoundAvatar.load(item.data.content.data.owner.avatar)
                } else {
                    holder.binding.ivRoundAvatar.gone()
                    holder.binding.ivAvatar.visible()
                    holder.binding.ivAvatar.load(item.data.content.data.owner.avatar)
                }

                holder.binding.ivBgPicture.run {
                    val imageHeight = calculateImageHeight(
                        item.data.content.data.width,
                        item.data.content.data.height
                    )
                    layoutParams.width = fragment.maxImageWidth
                    layoutParams.height = imageHeight
                    load(item.data.content.data.cover.feed)
                }

                holder.binding.tvDescription.text = item.data.content.data.description
                holder.binding.tvNickName.text = item.data.content.data.owner.nickname
                holder.binding.tvCollectionCount.text =
                    item.data.content.data.consumption.collectionCount.toString()
            }
        }
    }

    /**
     * holder部分
     */
    class SpecialHorizontalScrollCardItemCollectionViewHolder(val binding: ItemCommunityHorizontalScrollcardItemCollectionTypeBinding) :
        RecyclerView.ViewHolder(binding.root)

    class HorizontalScrollCardViewHolder(val binding: ItemCommunityHorizontalScrollcardTypeBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * 社区整个垂直列表的间隙
     */
    class ItemDecoration(val fragment: CommunityRecommendFragment) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val spanIndex = (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex

            outRect.top = fragment.bothSideSpace

            when (spanIndex) {
                0 -> {
                    outRect.left = fragment.bothSideSpace
                    outRect.right = fragment.middleSpace
                }

                else -> {
                    outRect.left = fragment.middleSpace
                    outRect.right = fragment.bothSideSpace
                }
            }
        }
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

    /**
     * 根据屏幕比例计算图片高
     *
     * @param originalWidth   服务器图片原始尺寸：宽
     * @param originalHeight  服务器图片原始尺寸：高
     * @return 根据比例缩放后的图片高
     */
    private fun calculateImageHeight(originalWidth: Int, originalHeight: Int): Int {
        //服务器数据异常处理
        if (originalWidth == 0 || originalHeight == 0) {
            LogUtils.d(TAG, ResourceUtils.getString(R.string.image_size_error))
            return fragment.maxImageWidth
        }
        return fragment.maxImageWidth * originalHeight / originalWidth
    }

    /**
     * 主题创作、话题讨论的adapter
     */
    class HorizontalScrollCardItemCollectionAdapter(
        var fragment: CommunityRecommendFragment,
        var itemList: List<CommunityRecommendData.Item.Data.Item>
    ) :
        RecyclerView.Adapter<HorizontalScrollCardItemCollectionAdapter.InnerHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): InnerHolder {
            return InnerHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_community_horizontal_scroll_card_itemcollection_item_type,
                    parent, false
                )
            )
        }

        class InnerHolder(view: View) : RecyclerView.ViewHolder(view) {
            val ivBgPicture: AppCompatImageView = view.findViewById(R.id.ivBgPicture)
            val tvTitle: TypefaceTextView = view.findViewById(R.id.tvTitle)
            val tvSubTitle: TypefaceTextView = view.findViewById(R.id.tvSubTitle)
        }

        override fun onBindViewHolder(
            holder: InnerHolder,
            position: Int
        ) {
            val item = itemList[position]
            holder.ivBgPicture.layoutParams.width = fragment.maxImageWidth
            holder.ivBgPicture.load(item.data.bgPicture)
            holder.tvTitle.text = item.data.title
            holder.tvSubTitle.text = item.data.subTitle
        }

        override fun getItemCount(): Int {
            return itemList.size
        }
    }

    /**
     * 轮播图的adapter
     */
    inner class BannerViewPager : BaseBannerAdapter<CommunityRecommendData.Item.Data.Item>() {
        override fun bindData(
            holder: BaseViewHolder<CommunityRecommendData.Item.Data.Item>,
            data: CommunityRecommendData.Item.Data.Item,
            position: Int,
            pageSize: Int
        ) {
            val ivPicture = holder.findViewById<ImageView>(R.id.ivPicture)
            ivPicture.load(data.data.image)
        }

        override fun getLayoutId(viewType: Int): Int {
            return R.layout.item_banner_item_type
        }
    }

    /**
     * 瀑布流布局的holder
     */
    inner class FollowCardViewHolder(val binding: ItemCommunityColumnsCardFollowCardTypeBinding) :
        RecyclerView.ViewHolder(binding.root)
}
