data class HomeRecommendData(
    val adExist: Boolean, // false
    val count: Int, // 14
    val itemList: List<Item>,
    val nextPageUrl: String, // http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=1&isTag=true&adIndex=5
    val total: Int // 0
) {
    data class Item(
        val adIndex: Int, // -1
        val `data`: Data,
        val id: Int, // 0
        val tag: Any, // null
        val trackingData: Any, // null
        val type: String // squareCardCollection
    ) {
        data class Data(
            val actionUrl: String, // eyepetizer://tag/44/?title=5%20%E5%88%86%E9%92%9F%E6%96%B0%E7%9F%A5
            val ad: Boolean, // false
            val adTrack: List<Any>,
            val author: Author,
            val brandWebsiteInfo: Any, // null
            val campaign: Any, // null
            val category: String, // 运动
            val collected: Boolean, // false
            val consumption: Consumption,
            val content: Content,
            val count: Int, // 10
            val cover: Cover,
            val dataType: String, // ItemCollection
            val date: Long, // 1451102400000
            val description: String, // 多亏了现代科技的发展，这些爱作死的人不仅可以将自己的作死实况记录下来，还可以分享给更多的人了。这支混剪视频集合了2015 年那些爱作死、不怕死的大神们的各式精彩花样。From Martin Scoreback
            val descriptionEditor: String, // 多亏了现代科技的发展，这些爱作死的人不仅可以将自己的作死实况记录下来，还可以分享给更多的人了。这支混剪视频集合了2015 年那些爱作死、不怕死的大神们的各式精彩花样。From Martin Scoreback
            val descriptionPgc: String, // 爬上 650 米高的上海中心大厦
            val duration: Int, // 289
            val favoriteAdTrack: Any, // null
            val follow: Any, // null
            val footer: Any, // null
            val header: Header,
            val id: Int, // 0
            val idx: Int, // 0
            val ifLimitVideo: Boolean, // false
            val itemList: List<Item>,
            val label: Any, // null
            val labelList: List<Any>,
            val lastViewTime: Any, // null
            val library: String, // DAILY
            val playInfo: List<PlayInfo>,
            val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=3284&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss&udid=
            val played: Boolean, // false
            val playlists: Any, // null
            val promotion: Any, // null
            val provider: Provider,
            val reallyCollected: Boolean, // false
            val recallSource: String,
            val recall_source: String,
            val releaseTime: Long, // 1451102400000
            val remark: String, // https://www.xinpianchang.com/a12296530?from=ArticleList
            val resourceType: String, // video
            val searchWeight: Int, // 0
            val shareAdTrack: Any, // null
            val slogan: Any, // null
            val src: Int, // 7
            val subTitle: Any, // null
            val subtitles: List<Any>,
            val tags: List<Tag>,
            val text: String, // 5 分钟新知
            val thumbPlayUrl: Any, // null
            val title: String, // 2015 最作死的那些人
            val titlePgc: String, // 爬上 650 米高的上海中心大厦
            val type: String, // header5
            val videoPosterBean: VideoPosterBean,
            val waterMarks: Any, // null
            val webAdTrack: Any, // null
            val webUrl: WebUrl
        ) {
            data class Author(
                val adTrack: Any, // null
                val approvedNotReadyVideoCount: Int, // 0
                val description: String, // 开眼运动精选
                val expert: Boolean, // false
                val follow: Follow,
                val icon: String, // http://img.kaiyanapp.com/f2449da39a584c982866b0636bd30c58.png?imageMogr2/quality/60/format/jpg
                val id: Int, // 2003
                val ifPgc: Boolean, // true
                val latestReleaseTime: Long, // 1672448419000
                val link: String,
                val name: String, // 开眼运动精选
                val recSort: Int, // 0
                val shield: Shield,
                val videoNum: Int // 629
            ) {
                data class Follow(
                    val followed: Boolean, // false
                    val itemId: Int, // 2003
                    val itemType: String // author
                )

                data class Shield(
                    val itemId: Int, // 2003
                    val itemType: String, // author
                    val shielded: Boolean // false
                )
            }

            data class Consumption(
                val collectionCount: Int, // 45811
                val realCollectionCount: Int, // 5627
                val replyCount: Int, // 692
                val shareCount: Int // 57736
            )

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
                    val category: String, // 科技
                    val collected: Boolean, // false
                    val consumption: Consumption,
                    val cover: Cover,
                    val dataType: String, // VideoBeanForClient
                    val date: Long, // 1488162878000
                    val description: String, // 通过行星及恒星的大小对比，能深刻体会到：人类，不太阳系真的太渺小了！所以，你在焦虑些什么呢？From MORN1415
                    val descriptionEditor: String, // 通过行星及恒星的大小对比，能深刻体会到：人类，不太阳系真的太渺小了！所以，你在焦虑些什么呢？From MORN1415
                    val descriptionPgc: String, // 该视频展现了行星及恒星的大小对比！整个画面十分震撼，人类，不太阳系真的太渺小了！
                    val duration: Int, // 378
                    val favoriteAdTrack: Any, // null
                    val id: Int, // 14796
                    val idx: Int, // 0
                    val ifLimitVideo: Boolean, // false
                    val label: Any, // null
                    val labelList: List<Any>,
                    val lastViewTime: Any, // null
                    val library: String, // DAILY
                    val playInfo: List<PlayInfo>,
                    val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=14796&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss&udid=
                    val played: Boolean, // false
                    val playlists: Any, // null
                    val promotion: Any, // null
                    val provider: Provider,
                    val reallyCollected: Boolean, // false
                    val recallSource: String,
                    val recall_source: String,
                    val releaseTime: Long, // 1488162878000
                    val remark: String,
                    val resourceType: String, // video
                    val searchWeight: Int, // 0
                    val shareAdTrack: Any, // null
                    val slogan: String, // 人类不过是尘埃
                    val src: Int, // 7
                    val subtitles: List<Any>,
                    val tags: List<Tag>,
                    val thumbPlayUrl: String,
                    val title: String, // 震撼你的视野：行星及恒星的大小对比
                    val titlePgc: String, // 震撼你的视野新版行星及恒星的大小对比
                    val type: String, // NORMAL
                    val videoPosterBean: VideoPosterBean,
                    val waterMarks: Any, // null
                    val webAdTrack: Any, // null
                    val webUrl: WebUrl
                ) {
                    data class Author(
                        val adTrack: Any, // null
                        val approvedNotReadyVideoCount: Int, // 0
                        val description: String, // 跟随天文君一起旅行宇宙，探索未知的世界！公众号ID：tianwenyizu
                        val expert: Boolean, // false
                        val follow: Follow,
                        val icon: String, // http://img.kaiyanapp.com/e0ad67901e14cc87c66558869bf6fbf4.png?imageMogr2/quality/60/format/jpg
                        val id: Int, // 400
                        val ifPgc: Boolean, // true
                        val latestReleaseTime: Long, // 1649682821000
                        val link: String,
                        val name: String, // 天文一族
                        val recSort: Int, // 0
                        val shield: Shield,
                        val videoNum: Int // 80
                    ) {
                        data class Follow(
                            val followed: Boolean, // false
                            val itemId: Int, // 400
                            val itemType: String // author
                        )

                        data class Shield(
                            val itemId: Int, // 400
                            val itemType: String, // author
                            val shielded: Boolean // false
                        )
                    }

                    data class Consumption(
                        val collectionCount: Int, // 74484
                        val realCollectionCount: Int, // 10832
                        val replyCount: Int, // 1262
                        val shareCount: Int // 104116
                    )

                    data class Cover(
                        val blurred: String, // http://img.kaiyanapp.com/cee6447d64899aeba600fc91a2081d7a.jpeg?imageMogr2/quality/60/format/jpg
                        val detail: String, // http://img.kaiyanapp.com/3586a5420e4803557e221d5ebaeb8d04.png?imageMogr2/quality/60/format/jpg
                        val feed: String, // http://img.kaiyanapp.com/3586a5420e4803557e221d5ebaeb8d04.png?imageMogr2/quality/60/format/jpg
                        val homepage: String, // http://img.kaiyanapp.com/2aef6f53fa20bc418aefd6dbc85b2e42.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                        val sharing: Any // null
                    )

                    data class PlayInfo(
                        val height: Int, // 480
                        val name: String, // 标清
                        val type: String, // normal
                        val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=14796&resourceType=video&editionType=normal&source=aliyun&playUrlType=url_oss&udid=
                        val urlList: List<Url>,
                        val width: Int // 854
                    ) {
                        data class Url(
                            val name: String, // aliyun
                            val size: Int, // 53442646
                            val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=14796&resourceType=video&editionType=normal&source=aliyun&playUrlType=url_oss&udid=
                        )
                    }

                    data class Provider(
                        val alias: String, // PGC
                        val icon: String,
                        val name: String // PGC
                    )

                    data class Tag(
                        val actionUrl: String, // eyepetizer://tag/44/?title=5%20%E5%88%86%E9%92%9F%E6%96%B0%E7%9F%A5
                        val adTrack: Any, // null
                        val bgPicture: String, // http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg
                        val childTagIdList: Any, // null
                        val childTagList: Any, // null
                        val communityIndex: Int, // 0
                        val desc: String, // 大千世界，总有你不知道的
                        val haveReward: Boolean, // false
                        val headerImage: String, // http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg
                        val id: Int, // 44
                        val ifNewest: Boolean, // false
                        val name: String, // 5 分钟新知
                        val newestEndTime: Any, // null
                        val tagRecType: String // IMPORTANT
                    )

                    data class VideoPosterBean(
                        val fileSizeStr: String, // 3.01MB
                        val scale: Double, // 0.725
                        val url: String // http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/ea137fbaef4800c8b5b1b9b35ba56f93.mp4
                    )

                    data class WebUrl(
                        val forWeibo: String, // http://wandou.im/3m62x5
                        val raw: String // http://www.eyepetizer.net/detail.html?vid=14796
                    )
                }
            }

            data class Cover(
                val blurred: String, // http://img.kaiyanapp.com/10966a7fa0eee98759f050c7f5448628.jpeg?imageMogr2/quality/100
                val detail: String, // http://img.kaiyanapp.com/522d2216b5f8aca639c296d71ac78753.jpeg?imageMogr2/quality/100
                val feed: String, // http://img.kaiyanapp.com/522d2216b5f8aca639c296d71ac78753.jpeg?imageMogr2/quality/100
                val homepage: String, // http://img.kaiyanapp.com/f061ac06b3f8e6d913c170d53e1e9303.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                val sharing: Any // null
            )

            data class Header(
                val actionUrl: String, // eyepetizer://feed?tabIndex=2
                val cover: Any, // null
                val description: String, // #科技 / 收录于 每日编辑精选
                val font: String, // bigBold
                val icon: String, // http://img.kaiyanapp.com/e0ad67901e14cc87c66558869bf6fbf4.png?imageMogr2/quality/60/format/jpg
                val iconType: String, // round
                val id: Int, // 5
                val label: Any, // null
                val labelList: Any, // null
                val rightText: String, // 查看往期
                val showHateVideo: Boolean, // false
                val subTitle: String, // THURSDAY, JANUARY 5
                val subTitleFont: String, // lobster
                val textAlign: String, // left
                val time: Long, // 1488162878000
                val title: String // 开眼编辑精选
            )

            data class Item(
                val adIndex: Int, // -1
                val `data`: Data,
                val id: Int, // 0
                val tag: Any, // null
                val trackingData: Any, // null
                val type: String // followCard
            ) {
                data class Data(
                    val adTrack: List<Any>,
                    val content: Content,
                    val dataType: String, // FollowCard
                    val header: Header
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
                            val category: String, // 旅行
                            val collected: Boolean, // false
                            val consumption: Consumption,
                            val cover: Cover,
                            val dataType: String, // VideoBeanForClient
                            val date: Long, // 1672880400000
                            val description: String, // 作者在冰岛度过美好时光的电影旅行短片。冰岛风光无限，没有人能够一次穷尽冰岛旅行的所有美好与乐趣，冰岛不同季节的景色差异极大，许多美景也无法一次兼得。当然，除了冰岛极光以外，这里还有午夜阳光、天然温泉、黄金圈观光、冰岛马和诱人的冰岛美食等，数不胜数。From Berre verresen
                            val descriptionEditor: String, // 作者在冰岛度过美好时光的电影旅行短片。冰岛风光无限，没有人能够一次穷尽冰岛旅行的所有美好与乐趣，冰岛不同季节的景色差异极大，许多美景也无法一次兼得。当然，除了冰岛极光以外，这里还有午夜阳光、天然温泉、黄金圈观光、冰岛马和诱人的冰岛美食等，数不胜数。From Berre verresen
                            val descriptionPgc: String,
                            val duration: Int, // 218
                            val favoriteAdTrack: Any, // null
                            val id: Int, // 315844
                            val idx: Int, // 0
                            val ifLimitVideo: Boolean, // false
                            val label: Any, // null
                            val labelList: List<Any>,
                            val lastViewTime: Any, // null
                            val library: String, // DAILY
                            val playInfo: List<PlayInfo>,
                            val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=315844&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss&udid=
                            val played: Boolean, // false
                            val playlists: Any, // null
                            val promotion: Any, // null
                            val provider: Provider,
                            val reallyCollected: Boolean, // false
                            val recallSource: Any, // null
                            val recall_source: Any, // null
                            val releaseTime: Long, // 1672880400000
                            val remark: String, // https://www.xinpianchang.com/a12300912?from=ArticleList，一个新的开始
                            val resourceType: String, // video
                            val searchWeight: Int, // 0
                            val shareAdTrack: Any, // null
                            val slogan: String,
                            val src: Any, // null
                            val subtitles: List<Any>,
                            val tags: List<Tag>,
                            val thumbPlayUrl: String,
                            val title: String, // 冰岛电影式旅行短片，这世界只剩下我们
                            val titlePgc: String, // 旅行 冰岛
                            val type: String, // NORMAL
                            val videoPosterBean: VideoPosterBean,
                            val waterMarks: Any, // null
                            val webAdTrack: Any, // null
                            val webUrl: WebUrl
                        ) {
                            data class Author(
                                val adTrack: Any, // null
                                val approvedNotReadyVideoCount: Int, // 0
                                val description: String, // 发现世界的奇妙和辽阔
                                val expert: Boolean, // false
                                val follow: Follow,
                                val icon: String, // http://img.kaiyanapp.com/75bc791c5f6cc239d6056e0a52d077fd.jpeg?imageMogr2/quality/60/format/jpg
                                val id: Int, // 2164
                                val ifPgc: Boolean, // true
                                val latestReleaseTime: Long, // 1672880400000
                                val link: String,
                                val name: String, // 开眼旅行精选
                                val recSort: Int, // 0
                                val shield: Shield,
                                val videoNum: Int // 884
                            ) {
                                data class Follow(
                                    val followed: Boolean, // false
                                    val itemId: Int, // 2164
                                    val itemType: String // author
                                )

                                data class Shield(
                                    val itemId: Int, // 2164
                                    val itemType: String, // author
                                    val shielded: Boolean // false
                                )
                            }

                            data class Consumption(
                                val collectionCount: Int, // 101
                                val realCollectionCount: Int, // 151
                                val replyCount: Int, // 5
                                val shareCount: Int // 79
                            )

                            data class Cover(
                                val blurred: String, // http://img.kaiyanapp.com/95ad13adf2165ccd400639460605c8a1.jpeg?imageMogr2/quality/60/format/jpg
                                val detail: String, // http://img.kaiyanapp.com/1648db696c9a4b608ce475ef9140a72e.jpeg?imageMogr2/quality/60/format/jpg
                                val feed: String, // http://img.kaiyanapp.com/1648db696c9a4b608ce475ef9140a72e.jpeg?imageMogr2/quality/60/format/jpg
                                val homepage: String, // http://img.kaiyanapp.com/923dd30eb36ff0f5481f9a0da96c399c.jpeg?imageMogr2/quality/60/format/jpg
                                val sharing: Any // null
                            )

                            data class PlayInfo(
                                val height: Int, // 720
                                val name: String, // 高清
                                val type: String, // high
                                val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=315932&resourceType=video&editionType=high&source=aliyun&playUrlType=url_oss&udid=
                                val urlList: List<Url>,
                                val width: Int // 1280
                            ) {
                                data class Url(
                                    val name: String, // aliyun
                                    val size: Int, // 9762719
                                    val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=315932&resourceType=video&editionType=high&source=aliyun&playUrlType=url_oss&udid=
                                )
                            }

                            data class Provider(
                                val alias: String, // youtube
                                val icon: String, // http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
                                val name: String // YouTube
                            )

                            data class Tag(
                                val actionUrl: String, // eyepetizer://tag/10/?title=%E8%B7%9F%E7%9D%80%E5%BC%80%E7%9C%BC%E7%9C%8B%E4%B8%96%E7%95%8C
                                val adTrack: Any, // null
                                val bgPicture: String, // http://img.kaiyanapp.com/7ea328a893aa1f092b9328a53494a267.png?imageMogr2/quality/60/format/jpg
                                val childTagIdList: Any, // null
                                val childTagList: Any, // null
                                val communityIndex: Int, // 14
                                val desc: String, // 去你想去的地方，发现世界的美
                                val haveReward: Boolean, // false
                                val headerImage: String, // http://img.kaiyanapp.com/50dab5468ecd2dbe5eb99dab5d608a0a.jpeg?imageMogr2/quality/60/format/jpg
                                val id: Int, // 10
                                val ifNewest: Boolean, // false
                                val name: String, // 跟着开眼看世界
                                val newestEndTime: Any, // null
                                val tagRecType: String // IMPORTANT
                            )

                            data class VideoPosterBean(
                                val fileSizeStr: String, // 2.84MB
                                val scale: Double, // 0.725
                                val url: String // http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/eb3b9f150daed03600b7a1d69d3634cc.mp4
                            )

                            data class WebUrl(
                                val forWeibo: String, // https://m.eyepetizer.net/u1/video-detail?video_id=315844&resource_type=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
                                val raw: String // http://www.eyepetizer.net/detail.html?vid=315844
                            )
                        }
                    }

                    data class Header(
                        val actionUrl: String, // eyepetizer://pgc/detail/2164/?title=%E5%BC%80%E7%9C%BC%E6%97%85%E8%A1%8C%E7%B2%BE%E9%80%89&userType=PGC&tabIndex=1
                        val cover: Any, // null
                        val description: String,
                        val font: Any, // null
                        val icon: String, // http://img.kaiyanapp.com/75bc791c5f6cc239d6056e0a52d077fd.jpeg?imageMogr2/quality/60/format/jpg
                        val iconType: String, // round
                        val id: Int, // 315844
                        val label: Any, // null
                        val labelList: Any, // null
                        val rightText: Any, // null
                        val showHateVideo: Boolean, // false
                        val subTitle: Any, // null
                        val subTitleFont: Any, // null
                        val textAlign: String, // left
                        val time: Long, // 1672880400000
                        val title: String // 冰岛电影式旅行短片，这世界只剩下我们
                    )
                }
            }

            data class PlayInfo(
                val height: Int, // 720
                val name: String, // 高清
                val type: String, // high
                val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=3284&resourceType=video&editionType=high&source=aliyun&playUrlType=url_oss&udid=
                val urlList: List<Url>,
                val width: Int // 1280
            ) {
                data class Url(
                    val name: String, // aliyun
                    val size: Int, // 77262598
                    val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=3284&resourceType=video&editionType=high&source=aliyun&playUrlType=url_oss&udid=
                )
            }

            data class Provider(
                val alias: String, // youtube
                val icon: String, // http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
                val name: String // YouTube
            )

            data class Tag(
                val actionUrl: String, // eyepetizer://tag/44/?title=5%20%E5%88%86%E9%92%9F%E6%96%B0%E7%9F%A5
                val adTrack: Any, // null
                val bgPicture: String, // http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg
                val childTagIdList: Any, // null
                val childTagList: Any, // null
                val communityIndex: Int, // 0
                val desc: String, // 大千世界，总有你不知道的
                val haveReward: Boolean, // false
                val headerImage: String, // http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg
                val id: Int, // 44
                val ifNewest: Boolean, // false
                val name: String, // 5 分钟新知
                val newestEndTime: Any, // null
                val tagRecType: String // IMPORTANT
            )

            data class VideoPosterBean(
                val fileSizeStr: String, // 6.04MB
                val scale: Double, // 0.725
                val url: String // http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/887ab12377e87ee5a192bd133405d29b.mp4
            )

            data class WebUrl(
                val forWeibo: String, // http://wandou.im/vpibb
                val raw: String // http://www.eyepetizer.net/detail.html?vid=3284
            )
        }
    }
}