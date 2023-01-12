package com.shanshan.eyepetizer.data

/**
 * 首页 发现列表
 */

data class DiscoveryData(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: Any,
    val total: Int
)

data class Item(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class Data(
    val actionUrl: Any,
    val adTrack: Any,
    val count: Int,
    val dataType: String,
    val description: String,
    val duration: Int,
    val cover: Cover,
    val expert: Boolean,
    val follow: Follow,
    val footer: Any,
    val haveReward: Boolean,
    val header: Header,
    val icon: String,
    val iconType: String,
    val id: Int,
    val ifNewest: Boolean,
    val ifPgc: Boolean,
    val ifShowNotificationIcon: Boolean,
    val itemList: List<ItemX>,
    val medalIcon: Boolean,
    val newestEndTime: Any,
    val rightText: String,
    val subTitle: Any,
    val switchStatus: Boolean,
    val text: String,
    val title: String,
    val type: String,
    val uid: Int
)

data class Header(
    val actionUrl: Any,
    val cover: Any,
    val font: String,
    val id: Int,
    val label: Any,
    val labelList: Any,
    val rightText: String,
    val subTitle: Any,
    val subTitleFont: Any,
    val textAlign: String,
    val title: String
)

data class ItemX(
    val adIndex: Int,
    val `data`: DataX,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class DataX(
    val actionUrl: String,
    val adTrack: List<Any>,
    val autoPlay: Boolean,
    val dataType: String,
    val description: String,
    val header: HeaderX,
    val id: Int,
    val image: String,
    val label: Label,
    val labelList: List<Any>,
    val shade: Boolean,
    val title: String
)

data class HeaderX(
    val actionUrl: Any,
    val cover: Any,
    val description: Any,
    val font: Any,
    val icon: Any,
    val id: Int,
    val label: Any,
    val labelList: Any,
    val rightText: Any,
    val subTitle: Any,
    val subTitleFont: Any,
    val textAlign: String,
    val title: Any
)

data class Label(
    val card: String,
    val detail: Any,
    val text: String
)

data class Follow(
    val itemType: String,
    val itemId: Int,
    val followed: Boolean
)

data class Cover(
    val blurred: String, // http://img.kaiyanapp.com/10966a7fa0eee98759f050c7f5448628.jpeg?imageMogr2/quality/100
    val detail: String, // http://img.kaiyanapp.com/522d2216b5f8aca639c296d71ac78753.jpeg?imageMogr2/quality/100
    val feed: String, // http://img.kaiyanapp.com/522d2216b5f8aca639c296d71ac78753.jpeg?imageMogr2/quality/100
    val homepage: String, // http://img.kaiyanapp.com/f061ac06b3f8e6d913c170d53e1e9303.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
    val sharing: Any // null
)