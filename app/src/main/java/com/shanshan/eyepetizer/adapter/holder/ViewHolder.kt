package com.shanshan.eyepetizer.adapter.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.data.HomeDailyData
import com.shanshan.eyepetizer.data.Item
import com.shanshan.eyepetizer.data.ItemX
import com.shanshan.eyepetizer.databinding.ItemFollowCardTypeBinding
import com.shanshan.eyepetizer.databinding.ItemVideoSmallCardTypeBinding
import com.shanshan.eyepetizer.utils.LogUtils
import com.zhpan.bannerview.BannerViewPager

/**
 * 项目全局的ViewHolder
 *  统一创建
 */

/**
 * 发现页中的viewHolder
 */
class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)

class HorizontalScrollCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val bannerViewPager: BannerViewPager<ItemX> = itemView.findViewById(R.id.banner_view)
}

class SpecialTextHeader5ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
}

class SpecialCardTypeBriefCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    val ivPicture = itemView.findViewById<ImageView>(R.id.ivPicture)
}

class SpecialColumnCardListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    val columnRecyclerView = itemView.findViewById<RecyclerView>(R.id.column_card_recyclerView)
}

//右侧有文本 查看全部的
class SpecialTextHeader7ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    val tvRightText = itemView.findViewById<TextView>(R.id.tv_right_text)
}

class SpecialTagBriefCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivPicture = itemView.findViewById<ImageView>(R.id.ivPicture)
    val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
}

class SpecialFollowCardViewHolder(val binding: ItemFollowCardTypeBinding) :
    RecyclerView.ViewHolder(binding.root)

class SpecialVideoSmallCardViewHolder(val binding: ItemVideoSmallCardTypeBinding) :
    RecyclerView.ViewHolder(binding.root)

/**
 * 条目类型
 */
class ItemViewType {
    companion object {
        const val UNKNOWN = -1              //未知类型，使用EmptyViewHolder容错处理。
        const val HorizontalScroll_TYPE = 0
        const val TEXT_CARD_HEADER4 = 4

        const val TEXT_CARD_HEADER5 = 5
        const val TEXT_CARD_HEADER7 = 7
        const val TEXT_CARD_HEADER8 = 8
        const val TEXT_CARD_FOOTER2 = 2
        const val TEXT_CARD_FOOTER3 = 3

        const val SPECIAL_SQUARE_CARD_COLLECTION =
            10   //type:specialSquareCardCollection -> dataType:ItemCollection
        const val CARD_TYPE_BRIEF = 11
        const val COLUMN_CARD_LIST = 12  //"type": "columnCardList",
        const val TAG_BRIEF_CARD = 13

        const val FOLLOW_CARD = 14

        const val VIDEO_SMALL_CARD = 15
    }
}

//RecyclerView工具类，用于获取ViewHolder和ItemViewType

object RecyclerViewUtil {

    fun getItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemViewType.HorizontalScroll_TYPE ->
                HorizontalScrollCardHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.home_banner_view,
                        parent,
                        false
                    )
                )
            //header5, such 热门分类
            ItemViewType.TEXT_CARD_HEADER5 ->
                SpecialTextHeader5ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_special_text_header5,
                        parent,
                        false
                    )
                )

            //BriefCard type
            ItemViewType.CARD_TYPE_BRIEF ->
                SpecialCardTypeBriefCardHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_special_type_brief_card,
                        parent,
                        false
                    )
                )

            //COLUMN_CARD_LIST,表示 开眼栏目
            ItemViewType.COLUMN_CARD_LIST ->
                SpecialColumnCardListHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_column_card_list,
                        parent,
                        false
                    )
                )

            //推荐主题 (右侧有查看全部)
            ItemViewType.TEXT_CARD_HEADER7 ->
                SpecialTextHeader7ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_special_text_header7,
                        parent,
                        false
                    )
                )

            ItemViewType.TAG_BRIEF_CARD ->
                SpecialTagBriefCardViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_tag_brief_card,
                        parent,
                        false
                    )
                )

            ItemViewType.FOLLOW_CARD ->
                SpecialFollowCardViewHolder(
                    ItemFollowCardTypeBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )

            ItemViewType.VIDEO_SMALL_CARD ->
                SpecialVideoSmallCardViewHolder(
                    ItemVideoSmallCardTypeBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )

            else -> EmptyViewHolder(View(parent.context))
        }
    }

    //获取条目类型,嵌套存在的，比如首页banner或者栏目这种
    private fun getItemViewType(type: String, dataType: String) = when (type) {
        "horizontalScrollCard" -> when (dataType) {
            "HorizontalScrollCard" -> ItemViewType.HorizontalScroll_TYPE
            else -> ItemViewType.UNKNOWN
        }

        "columnCardList" -> when (dataType) {
            "ItemCollection" -> ItemViewType.COLUMN_CARD_LIST
            else -> ItemViewType.UNKNOWN
        }

        "followCard" -> when (dataType) {
            "FollowCard" -> ItemViewType.FOLLOW_CARD
            else -> ItemViewType.UNKNOWN
        }

        "videoSmallCard" -> when (dataType) {
            "VideoBeanForClient" -> ItemViewType.VIDEO_SMALL_CARD
            else -> ItemViewType.UNKNOWN
        }

        else -> ItemViewType.UNKNOWN
    }

    //返回条目类型
    fun getItemViewType(item: Item): Int {
        return when (item.type) {
            "textCard" -> getTextCardType(item.data.type, item.data.dataType)

            "briefCard" -> getTextItemCardType(item.data.dataType, item.data.follow.itemType)

            "columnCardList" -> getItemViewType(item.type, item.data.dataType)

            else -> getItemViewType(item.type, item.data.dataType)
        }
    }

    /**
     * 获取每个标题下item的类型
     */
    private fun getTextItemCardType(dataType: String, followType: String): Int {
        return when (dataType) {
            "BriefCard" ->
                when (followType) {
                    "category" -> ItemViewType.CARD_TYPE_BRIEF
                    "author" -> ItemViewType.TAG_BRIEF_CARD
                    else -> ItemViewType.UNKNOWN
                }
            "TagBriefCard" -> {
                LogUtils.d("TAG", "TagBriefCard")
                ItemViewType.TAG_BRIEF_CARD
            }
            else -> ItemViewType.UNKNOWN
        }
    }

    /**
     * 获取标题header的item类型
     */
    private fun getTextCardType(type: String, dataType: String): Int {
        return when (type) {
            "header4" -> ItemViewType.TEXT_CARD_HEADER4
            "header5" -> ItemViewType.TEXT_CARD_HEADER5
            "header7" -> ItemViewType.TEXT_CARD_HEADER7
            "header8" -> ItemViewType.TEXT_CARD_HEADER8
            "footer2" -> ItemViewType.TEXT_CARD_FOOTER2
            "footer3" -> ItemViewType.TEXT_CARD_FOOTER3
            else -> ItemViewType.UNKNOWN
        }
    }

    fun getItemViewType(item: HomeRecommendData.Item): Int {
        return when (item.type) {
            "textCard" -> getTextCardType(item.data.type, item.data.dataType)
            else -> getItemViewType(item.type, item.data.dataType)
        }
    }

    fun getItemViewType(item: HomeDailyData.Item): Int {
        return when (item.type) {
            "textCard" -> getTextCardType(item.data.type, item.data.dataType)
            else -> getItemViewType(item.type, item.data.dataType)
        }
    }
}