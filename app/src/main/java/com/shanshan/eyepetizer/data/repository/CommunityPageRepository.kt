package com.shanshan.eyepetizer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.data.CommunityFollowData
import com.shanshan.eyepetizer.data.CommunityRecommendData
import com.shanshan.eyepetizer.data.pagingsource.CommunityCommendPagingSource
import com.shanshan.eyepetizer.data.pagingsource.CommunityFollowPagingSource
import kotlinx.coroutines.flow.Flow

class CommunityPageRepository {

    /**
     * 社区--推荐
     */
    fun getCommunityRecommendPagingData(): Flow<PagingData<CommunityRecommendData.Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.COMMUNITY_RECOMMEND_PAGE_SIZE,
                initialLoadSize = Constants.COMMUNITY_RECOMMEND_PAGE_SIZE * 3
            ),
            pagingSourceFactory = { CommunityCommendPagingSource() }
        ).flow
    }

    /**
     * 社区-关注
     */
    fun getCommunityFollowPagingData(): Flow<PagingData<CommunityFollowData.Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.COMMUNITY_FOLLOW_PAGE_SIZE,
                initialLoadSize = Constants.COMMUNITY_FOLLOW_PAGE_SIZE * 3
            ),
            pagingSourceFactory = { CommunityFollowPagingSource() }
        ).flow
    }
}