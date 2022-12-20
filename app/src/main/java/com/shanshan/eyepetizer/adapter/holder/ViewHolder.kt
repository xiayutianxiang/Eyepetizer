package com.shanshan.eyepetizer.adapter.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.data.Item
import com.shanshan.eyepetizer.data.ItemX
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

/**
 * 发现页中条目类型
 */
class DiscoveryItemViewType {
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
    }
}

//RecyclerView工具类，用于获取ViewHolder和ItemViewType

object RecyclerViewUtil {

    fun getItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DiscoveryItemViewType.HorizontalScroll_TYPE ->
                HorizontalScrollCardHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.home_banner_view,
                        parent,
                        false
                    )
                )
            //header5, such 热门分类
            DiscoveryItemViewType.TEXT_CARD_HEADER5 ->
                SpecialTextHeader5ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_special_text_header5,
                        parent,
                        false
                    )
                )

            //BriefCard type
            DiscoveryItemViewType.CARD_TYPE_BRIEF ->
                SpecialCardTypeBriefCardHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_special_type_brief_card,
                        parent,
                        false
                    )
                )

            //COLUMN_CARD_LIST,表示 开眼栏目
            DiscoveryItemViewType.COLUMN_CARD_LIST ->
                SpecialColumnCardListHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_column_card_list,
                        parent,
                        false
                    )
                )

            //推荐主题 (右侧有查看全部)
            DiscoveryItemViewType.TEXT_CARD_HEADER7 ->
                SpecialTextHeader7ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_special_text_header7,
                        parent,
                        false
                    )
                )

            DiscoveryItemViewType.TAG_BRIEF_CARD ->
                SpecialTagBriefCardViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_tag_brief_card,
                        parent,
                        false
                    )
                )

            else -> EmptyViewHolder(View(parent.context))
        }
    }

    //获取条目类型,嵌套存在的，比如首页banner或者栏目这种
    private fun getItemViewType(type: String, dataType: String) = when (type) {
        "horizontalScrollCard" -> when (dataType) {
            "HorizontalScrollCard" -> DiscoveryItemViewType.HorizontalScroll_TYPE
            else -> DiscoveryItemViewType.UNKNOWN
        }

        "columnCardList" -> when (dataType) {
            "ItemCollection" -> DiscoveryItemViewType.COLUMN_CARD_LIST
            else -> DiscoveryItemViewType.UNKNOWN
        }

        else -> DiscoveryItemViewType.UNKNOWN
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
                    "category" -> DiscoveryItemViewType.CARD_TYPE_BRIEF
                    else -> DiscoveryItemViewType.UNKNOWN
                }
            "TagBriefCard" -> {
                LogUtils.d("TAG", "TagBriefCard")
                DiscoveryItemViewType.TAG_BRIEF_CARD
            }
            else -> DiscoveryItemViewType.UNKNOWN
        }
    }

    /**
     * 获取标题header的item类型
     */
    private fun getTextCardType(type: String, dataType: String): Int {
        return when (type) {
            "header4" -> DiscoveryItemViewType.TEXT_CARD_HEADER4
            "header5" -> DiscoveryItemViewType.TEXT_CARD_HEADER5
            "header7" -> DiscoveryItemViewType.TEXT_CARD_HEADER7
            "header8" -> DiscoveryItemViewType.TEXT_CARD_HEADER8
            "footer2" -> DiscoveryItemViewType.TEXT_CARD_FOOTER2
            "footer3" -> DiscoveryItemViewType.TEXT_CARD_FOOTER3
            else -> DiscoveryItemViewType.UNKNOWN
        }
    }
}