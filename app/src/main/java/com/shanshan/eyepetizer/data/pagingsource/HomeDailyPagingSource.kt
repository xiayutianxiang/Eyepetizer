package com.shanshan.eyepetizer.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.data.HomeDailyData
import com.shanshan.eyepetizer.network.RetrofitManager
import com.shanshan.eyepetizer.utils.LogUtils
import java.lang.Exception

class HomeDailyPagingSource : PagingSource<String, HomeDailyData.Item>() {

    companion object{
        private const val TAG = "HomeDailyPagingSource"
    }

    override fun getRefreshKey(state: PagingState<String, HomeDailyData.Item>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, HomeDailyData.Item> {
        return try {
            val page = params.key ?: Constants.WebUrl.DAILY_URL
            LogUtils.d(TAG, "page ---> $page")
            val repoResponse = RetrofitManager.apiService.getDaily(page)
            val repoItems = repoResponse.itemList
            LogUtils.d(TAG, "repoItems ---> $repoItems")
            val preKey = null
            val nextKey =
                if (repoItems.isNotEmpty() && repoResponse.nextPageUrl.isNotEmpty()) repoResponse.nextPageUrl else null
            LoadResult.Page(repoItems, preKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}