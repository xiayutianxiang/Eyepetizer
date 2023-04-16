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
        }
    }

    companion object {
        const val HOME_RECOMMEND_PAGE_SIZE = 5
        const val HOME_DAILY_PAGE_SIZE = 22
        const val COMMUNITY_RECOMMEND_PAGE_SIZE = 11
        const val COMMUNITY_FOLLOW_PAGE_SIZE = 5
    }
}