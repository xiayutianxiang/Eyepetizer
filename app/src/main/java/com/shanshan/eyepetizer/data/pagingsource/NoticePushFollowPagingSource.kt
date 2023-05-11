package com.shanshan.eyepetizer.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.data.NoticePushData
import com.shanshan.eyepetizer.network.RetrofitManager
import com.shanshan.eyepetizer.utils.LogUtils
import java.lang.Exception

class NoticePushFollowPagingSource : PagingSource<String, NoticePushData.Message>() {

    companion object {
        private const val TAG = "NoticePushFollowPagingSource"
    }

    override fun getRefreshKey(state: PagingState<String, NoticePushData.Message>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, NoticePushData.Message> {
        return try {
            val page = params.key ?: Constants.WebUrl.NOTICE_PUSH_URL   //加载链接，根据key是否为空判断
            val repoResponse = RetrofitManager.apiService.getNoticeFollow(page) //返回数据
            val repoItems = repoResponse.messageList
            LogUtils.d(TAG, "repoItems ---> $repoItems")
            val preKey = null
            val nextKey =
                if (repoItems.isEmpty() && repoResponse.nextPageUrl.isNotEmpty()) repoResponse.nextPageUrl else null
            LoadResult.Page(repoItems, preKey, nextKey) //返回值
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}