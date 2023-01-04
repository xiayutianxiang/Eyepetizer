package com.shanshan.eyepetizer.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.data.HomeRecommendData
import com.shanshan.eyepetizer.network.RetrofitManager
import com.shanshan.eyepetizer.utils.LogUtils

class HomeRecommendPagingSource : PagingSource<String, HomeRecommendData.Item>() {

    companion object{
        private const val TAG = "HomeSource"
    }

    override fun getRefreshKey(state: PagingState<String, HomeRecommendData.Item>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, HomeRecommendData.Item> {
        return try {
            val page = params.key ?: Constants.WebUrl.RECOMMEND_URL
            val repoResponse = RetrofitManager.apiService.getRecommend(page)
            val repoItems = repoResponse.itemList
            LogUtils.d(TAG,"repoItems ---> $repoItems")
            val preKey = null
            val nextKey =
                if (repoItems.isNotEmpty() && repoResponse.nextPageUrl.isNotEmpty()) repoResponse.nextPageUrl else null
            LoadResult.Page(repoItems, preKey, nextKey)
        } catch (e: Exception) {
            LogUtils.d(TAG,"e ---> $e")
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}