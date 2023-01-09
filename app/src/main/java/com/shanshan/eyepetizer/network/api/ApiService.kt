package com.shanshan.eyepetizer.network.api

import HomeRecommendData
import com.shanshan.eyepetizer.data.DiscoveryData
import com.shanshan.eyepetizer.data.HomeDailyData
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
    suspend fun getDaily(@Url url:String) : HomeDailyData
}