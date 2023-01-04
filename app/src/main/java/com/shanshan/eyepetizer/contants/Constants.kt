package com.shanshan.eyepetizer.contants

class Constants {

    //网页链接
    interface WebUrl {
        companion object {
            //通用BASE URL
            const val BASE_URL = "https://baobab.kaiyanapp.com/"

            //发现页URL
            const val DISCOVERY_URL = "api/v7/index/tab/discovery"

            //推荐页URL
            const val RECOMMEND_URL = "api/v5/index/tab/allRec"
            const val PAGE_SIZE = 50
        }
    }
}