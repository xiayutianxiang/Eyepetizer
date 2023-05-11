package com.shanshan.eyepetizer.network.api

import HomeRecommendData
import com.shanshan.eyepetizer.data.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    /**
     * 首页-发现列表
     */
    @GET
    fun getDiscovery(@Url url: String): Call<DiscoveryData>

    /**
     * 首页-推荐列表
     */
    @GET
    suspend fun getRecommend(@Url url: String): HomeRecommendData

    /**
     * 首页-日报列表
     */
    @GET
    suspend fun getDaily(@Url url: String): HomeDailyData

    /**
     * 社区-推荐列表
     */
    @GET
    suspend fun getCommunityCommend(@Url url: String): CommunityRecommendData

    /**
     * 社区关注列表
     */
    @GET
    suspend fun getCommunityFollow(@Url url: String) : CommunityFollowData

    /**
     * 通知列表
     */
    @GET
    suspend fun getNoticeFollow(@Url url: String) : NoticePushData
}