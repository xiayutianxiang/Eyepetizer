package com.shanshan.eyepetizer.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.data.CommunityFollowData
import com.shanshan.eyepetizer.network.RetrofitManager
import com.shanshan.eyepetizer.utils.LogUtils
import java.lang.Exception

class CommunityFollowPagingSource : PagingSource<String, CommunityFollowData.Item>() {

    companion object {
        private const val TAG = "CommunityFollowSource"
    }

    override fun getRefreshKey(state: PagingState<String, CommunityFollowData.Item>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, CommunityFollowData.Item> {
        return try {
            val page = params.key ?: Constants.WebUrl.COMMUNITY_FOLLOW_URL
            val repoResponse = RetrofitManager.apiService.getCommunityFollow(page)
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