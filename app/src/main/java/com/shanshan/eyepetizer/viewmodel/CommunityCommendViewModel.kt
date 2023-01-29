package com.shanshan.eyepetizer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shanshan.eyepetizer.data.CommunityRecommendData
import com.shanshan.eyepetizer.data.repository.CommunityPageRepository
import kotlinx.coroutines.flow.Flow

class CommunityCommendViewModel : ViewModel() {

    private val repository by lazy {
        CommunityPageRepository()
    }

    fun getCommunityRecommendContent() : Flow<PagingData<CommunityRecommendData.Item>> =
        repository.getCommunityRecommendPagingData().cachedIn(viewModelScope)
}