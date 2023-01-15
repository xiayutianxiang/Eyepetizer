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

            const val COMMUNITY_RECOMMEND_URL = "${BASE_URL}api/v7/community/tab/rec"
        }
    }

    companion object {
        const val RECOMMEND_PAGE_SIZE = 5
        const val DAILY_PAGE_SIZE = 22
    }
}