package com.shanshan.eyepetizer.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.data.HomeRecommendData
import com.shanshan.eyepetizer.data.pagingsource.HomeRecommendPagingSource
import com.shanshan.eyepetizer.network.RetrofitManager
import kotlinx.coroutines.flow.Flow
import retrofit2.await

class DiscoveryRepository {

    //获取发现页
    fun getHomeDiscoveryContent() =
        RetrofitManager.apiService.getDiscovery(Constants.WebUrl.DISCOVERY_URL)

    //获取首页推荐
    fun getHomeRecommendPagingData(): Flow<PagingData<HomeRecommendData.Item>> {
        return Pager(
            config = PagingConfig(Constants.PAGE_SIZE, prefetchDistance = Constants.PAGE_SIZE * 5),
            pagingSourceFactory = { HomeRecommendPagingSource() }
        ).flow
    }
}