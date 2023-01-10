package com.shanshan.eyepetizer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.data.HomeDailyData
import com.shanshan.eyepetizer.data.pagingsource.HomeDailyPagingSource
import com.shanshan.eyepetizer.data.pagingsource.HomeRecommendPagingSource
import com.shanshan.eyepetizer.network.RetrofitManager
import kotlinx.coroutines.flow.Flow

class HomePageRepository {

    //获取发现页
    fun getHomeDiscoveryContent() =
        RetrofitManager.apiService.getDiscovery(Constants.WebUrl.DISCOVERY_URL)

    //获取首页推荐
    fun getHomeRecommendPagingData(): Flow<PagingData<HomeRecommendData.Item>> {
        return Pager(
            config = PagingConfig(
                Constants.RECOMMEND_PAGE_SIZE,
                initialLoadSize = Constants.RECOMMEND_PAGE_SIZE * 3
            ),
            pagingSourceFactory = { HomeRecommendPagingSource() }
        ).flow
    }

    //获取首页日报
    fun getHomeDailyPagingData(): Flow<PagingData<HomeDailyData.Item>> {
        return Pager(
            config = PagingConfig(
                Constants.DAILY_PAGE_SIZE,
                initialLoadSize = Constants.DAILY_PAGE_SIZE * 3
            ),
            pagingSourceFactory = { HomeDailyPagingSource() }
        ).flow
    }
}