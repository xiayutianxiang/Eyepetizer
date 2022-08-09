package com.shanshan.eyepetizer.network.api

import com.shanshan.eyepetizer.data.DiscoveryData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    /**
     * 首页-发现列表
     */
    @GET
    fun getDiscovery(@Url url: String): Call<DiscoveryData>
}