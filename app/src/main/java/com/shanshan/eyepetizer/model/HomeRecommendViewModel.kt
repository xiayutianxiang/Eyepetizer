package com.shanshan.eyepetizer.model

import HomeRecommendData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shanshan.eyepetizer.model.repository.DiscoveryRepository
import kotlinx.coroutines.flow.Flow

class HomeRecommendViewModel : ViewModel() {

    private val repository by lazy {
        DiscoveryRepository()
    }

    fun getPagingData(): Flow<PagingData<HomeRecommendData.Item>> {
        return repository.getHomeRecommendPagingData().cachedIn(viewModelScope)
    }
}