package com.shanshan.eyepetizer.data

data class HomeDailyData(
    val adExist: Boolean, // false
    val count: Int, // 22
    val itemList: List<Item>,
    val nextPageUrl: String, // http://baobab.kaiyanapp.com/api/v5/index/tab/feed?date=1673053200000&num=2
    val total: Int // 0
) {
    data class Item(
        val adIndex: Int, // -1
        val `data`: Data,
        val id: Int, // 0
        val tag: Any, // null
        val trackingData: Any, // null
        val type: String // textCard
    ) {
        data class Data(
            val actionUrl: Any, // null
            val adTrack: List<Any>,
            val content: Content,
            val dataType: String, // TextCard
            val follow: Any, // null
            val header: Header,
            val id: Int, // 0
            val subTitle: Any, // null
            val text: String, // 今日开眼精选
            val type: String // header5
        ) {
            data class Content(
                val adIndex: Int, // -1
                val `data`: Data,
                val id: Int, // 0
                val tag: Any, // null
                val trackingData: Any, // null
                val type: String // video
            ) {
                data class Data(
                    val ad: Boolean, // false
                    val adTrack: List<Any>,
                    val author: Author,
                    val brandWebsiteInfo: Any, // null
                    val campaign: Any, // null
                    val category: String, // 广告
                    val collected: Boolean, // false
                    val consumption: Consumption,
                    val cover: Cover,
                    val dataType: String, // VideoBeanForClient
                    val date: Long, // 1673226000000
                    val description: String, // 日本 JT 公司「JAPAN TOBACCO INC」是世界第三大跨国烟草公司，也是日本唯一的烟草专卖公司，他们的广告传递的就是比较正向的价值观念。JT 这支以 a moment in Japan 为主题的商业广告，突出为他人着想并共度美好时光的主题，这也是日本流传下来的一项不间断的传统。From Bryant Hill
                    val descriptionEditor: String, // 日本 JT 公司「JAPAN TOBACCO INC」是世界第三大跨国烟草公司，也是日本唯一的烟草专卖公司，他们的广告传递的就是比较正向的价值观念。JT 这支以 a moment in Japan 为主题的商业广告，突出为他人着想并共度美好时光的主题，这也是日本流传下来的一项不间断的传统。From Bryant Hill
                    val descriptionPgc: String,
                    val duration: Int, // 180
                    val favoriteAdTrack: Any, // null
                    val id: Int, // 14826
                    val idx: Int, // 0
                    val ifLimitVideo: Boolean, // false
                    val label: Any, // null
                    val labelList: List<Any>,
                    val lastViewTime: Any, // null
                    val library: String, // DAILY
                    val playInfo: List<PlayInfo>,
                    val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=14826&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss&udid=
                    val played: Boolean, // false
                    val playlists: Any, // null
                    val promotion: Any, // null
                    val provider: Provider,
                    val reallyCollected: Boolean, // false
                    val recallSource: Any, // null
                    val recall_source: Any, // null
                    val releaseTime: Long, // 1673226015000
                    val remark: String, // https://www.youtube.com/watch?app=desktop&v=WH4nKPATfFQ
                    val resourceType: String, // video
                    val searchWeight: Int, // 0
                    val shareAdTrack: Any, // null
                    val slogan: String,
                    val src: Any, // null
                    val subtitles: List<Any>,
                    val tags: List<Tag>,
                    val thumbPlayUrl: String,
                    val title: String, // 唯美烟草广告：在日本的每一刻
                    val titlePgc: String,
                    val type: String, // NORMAL
                    val videoPosterBean: VideoPosterBean,
                    val waterMarks: Any, // null
                    val webAdTrack: Any, // null
                    val webUrl: WebUrl
                ) {
                    data class Author(
                        val adTrack: Any, // null
                        val approvedNotReadyVideoCount: Int, // 0
                        val description: String, // 我们精选世界最好看的广告，为全世界广告人的精彩创意点赞。
                        val expert: Boolean, // false
                        val follow: Follow,
                        val icon: String, // http://img.kaiyanapp.com/98beab66d3885a139b54f21e91817c4f.jpeg
                        val id: Int, // 2162
                        val ifPgc: Boolean, // true
                        val latestReleaseTime: Long, // 1673226021000
                        val link: String,
                        val name: String, // 全球广告精选
                        val recSort: Int, // 0
                        val shield: Shield,
                        val videoNum: Int // 2370
                    ) {
                        data class Follow(
                            val followed: Boolean, // false
                            val itemId: Int, // 2162
                            val itemType: String // author
                        )

                        data class Shield(
                            val itemId: Int, // 2162
                            val itemType: String, // author
                            val shielded: Boolean // false
                        )
                    }

                    data class Consumption(
                        val collectionCount: Int, // 80
                        val realCollectionCount: Int, // 80
                        val replyCount: Int, // 6
                        val shareCount: Int // 11
                    )

                    data class Cover(
                        val blurred: String, // http://img.kaiyanapp.com/22fb1c89c16ad638bdd939bd83db9526.jpeg?imageMogr2/quality/60/format/jpg
                        val detail: String, // http://img.kaiyanapp.com/367b8ac0f5ded39bb7f6d62e843c7cac.jpeg?imageMogr2/quality/60/format/jpg
                        val feed: String, // http://img.kaiyanapp.com/367b8ac0f5ded39bb7f6d62e843c7cac.jpeg?imageMogr2/quality/60/format/jpg
                        val homepage: String, // http://img.kaiyanapp.com/367b8ac0f5ded39bb7f6d62e843c7cac.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                        val sharing: Any // null
                    )

                    data class PlayInfo(
                        val height: Int, // 480
                        val name: String, // 标清
                        val type: String, // normal
                        val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=14826&resourceType=video&editionType=normal&source=aliyun&playUrlType=url_oss&udid=
                        val urlList: List<Url>,
                        val width: Int // 854
                    ) {
                        data class Url(
                            val name: String, // aliyun
                            val size: Int, // 10272871
                            val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=14826&resourceType=video&editionType=normal&source=aliyun&playUrlType=url_oss&udid=
                        )
                    }

                    data class Provider(
                        val alias: String, // youtube
                        val icon: String, // http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
                        val name: String // YouTube
                    )

                    data class Tag(
                        val actionUrl: String, // eyepetizer://tag/748/?title=%E8%BF%99%E4%BA%9B%E5%B9%BF%E5%91%8A%E8%B6%85%E6%9C%89%E6%A2%97
                        val adTrack: Any, // null
                        val bgPicture: String, // http://img.kaiyanapp.com/9056413cfeffaf0c841d894390aa8e08.jpeg?imageMogr2/quality/60/format/jpg
                        val childTagIdList: Any, // null
                        val childTagList: Any, // null
                        val communityIndex: Int, // 0
                        val desc: String, // 为广告人的精彩创意点赞
                        val haveReward: Boolean, // false
                        val headerImage: String, // http://img.kaiyanapp.com/ff0f6d0ad5f4b6211a3f746aaaffd916.jpeg?imageMogr2/quality/60/format/jpg
                        val id: Int, // 748
                        val ifNewest: Boolean, // false
                        val name: String, // 这些广告超有梗
                        val newestEndTime: Any, // null
                        val tagRecType: String // IMPORTANT
                    )

                    data class VideoPosterBean(
                        val fileSizeStr: String, // 3.41MB
                        val scale: Double, // 0.725
                        val url: String // http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/4279e86d4a2a9526afd3371553810529.mp4
                    )

                    data class WebUrl(
                        val forWeibo: String, // http://wandou.im/3m67om
                        val raw: String // http://www.eyepetizer.net/detail.html?vid=14826
                    )
                }
            }

            data class Header(
                val actionUrl: String, // eyepetizer://pgc/detail/2162/?title=%E5%85%A8%E7%90%83%E5%B9%BF%E5%91%8A%E7%B2%BE%E9%80%89&userType=PGC&tabIndex=1
                val cover: Any, // null
                val description: String, // #广告 / 收录于 每日编辑精选
                val font: Any, // null
                val icon: String, // http://img.kaiyanapp.com/98beab66d3885a139b54f21e91817c4f.jpeg
                val iconType: String, // round
                val id: Int, // 14826
                val label: Any, // null
                val labelList: Any, // null
                val rightText: Any, // null
                val showHateVideo: Boolean, // false
                val subTitle: Any, // null
                val subTitleFont: Any, // null
                val textAlign: String, // left
                val time: Long, // 1673226015000
                val title: String // 全球广告精选
            )
        }
    }
}