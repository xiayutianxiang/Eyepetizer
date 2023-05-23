package com.shanshan.eyepetizer.contants

class Constants {

    //网页链接
    interface WebUrl {
        companion object {
            //通用BASE URL
            const val BASE_URL = "http://baobab.kaiyanapp.com/"

            //发现页URL
            const val DISCOVERY_URL = "${BASE_URL}api/v7/index/tab/discovery"

            //推荐页URL
            const val RECOMMEND_URL = "${BASE_URL}api/v5/index/tab/allRec"

            const val DAILY_URL = "${BASE_URL}api/v5/index/tab/feed"

            //社区-推荐列表
            const val COMMUNITY_RECOMMEND_URL = "${BASE_URL}api/v7/community/tab/rec"

            //社区-关注列表
            const val COMMUNITY_FOLLOW_URL = "${BASE_URL}api/v6/community/tab/follow"

            //通知推荐列表
            const val NOTICE_PUSH_URL = "${BASE_URL}api/v3/messages"
        }
    }

    companion object {
        const val HOME_RECOMMEND_PAGE_SIZE = 5
        const val HOME_DAILY_PAGE_SIZE = 22
        const val COMMUNITY_RECOMMEND_PAGE_SIZE = 11
        const val COMMUNITY_FOLLOW_PAGE_SIZE = 5


        const val EXTRA_VIDEOINFO = "videoInfo"
        const val EXTRA_VIDEO_ID = "videoId"
    }

    interface ActionUrl {

        companion object {

            const val TAG = "eyepetizer://tag/"

            const val DETAIL = "eyepetizer://detail/"

            const val RANKLIST = "eyepetizer://ranklist/"

            const val WEBVIEW = "eyepetizer://webview/?title="

            const val REPLIES_HOT = "eyepetizer://replies/hot?"

            const val TOPIC_DETAIL = "eyepetizer://topic/detail?"

            const val COMMON_TITLE = "eyepetizer://common/?title"

            const val LT_DETAIL = "eyepetizer://lightTopic/detail/"

            const val CM_TOPIC_SQUARE = "eyepetizer://community/topicSquare"

            const val HP_NOTIFI_TAB_ZERO = "eyepetizer://homepage/notification?tabIndex=0"

            const val CM_TAGSQUARE_TAB_ZERO = "eyepetizer://community/tagSquare?tabIndex=0"

            const val CM_TOPIC_SQUARE_TAB_ZERO = "eyepetizer://community/tagSquare?tabIndex=0"

            const val HP_SEL_TAB_TWO_NEWTAB_MINUS_THREE = "eyepetizer://homepage/selected?tabIndex=2&newTabIndex=-3"
        }
    }
}