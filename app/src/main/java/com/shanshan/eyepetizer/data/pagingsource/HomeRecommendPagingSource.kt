package com.shanshan.eyepetizer.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.network.RetrofitManager
import com.shanshan.eyepetizer.utils.LogUtils

class HomeRecommendPagingSource : PagingSource<String, HomeRecommendData.Item>() {

    companion object {
        private const val TAG = "HomeSource"
    }

    override fun getRefreshKey(state: PagingState<String, HomeRecommendData.Item>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, HomeRecommendData.Item> {
        return try {

            /**
             * 这个下一页的链接，会重复，最后一个是http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=0&isTag=false
             * 此后会继续加载之前的数据，所以等加载到这个数据的时候下一页链接设为null
             */

            val page = params.key ?: Constants.WebUrl.RECOMMEND_URL
            LogUtils.d(TAG, "page ---> $page")
            val repoResponse = RetrofitManager.apiService.getRecommend(page)
            val repoItems = repoResponse.itemList
            LogUtils.d(TAG, "repoItems ---> $repoItems")
            val preKey = null
            val nextKey =
                if (repoItems.isNotEmpty() && repoResponse.nextPageUrl.isNotEmpty() && !repoResponse.nextPageUrl.endsWith("false")) repoResponse.nextPageUrl else null
            LoadResult.Page(repoItems, preKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}