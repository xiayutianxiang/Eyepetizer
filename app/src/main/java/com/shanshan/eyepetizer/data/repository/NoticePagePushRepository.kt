package com.shanshan.eyepetizer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.data.NoticePushData
import com.shanshan.eyepetizer.data.pagingsource.NoticePushFollowPagingSource
import kotlinx.coroutines.flow.Flow

class NoticePagePushRepository {

    /**
     * 通知--推送
     */
    fun getNoticePushPagingData(): Flow<PagingData<NoticePushData.Message>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.COMMUNITY_RECOMMEND_PAGE_SIZE,
                initialLoadSize = Constants.COMMUNITY_RECOMMEND_PAGE_SIZE * 2
            ),
            pagingSourceFactory = { NoticePushFollowPagingSource() }
        ).flow
    }
}