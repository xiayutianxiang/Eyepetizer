package com.shanshan.eyepetizer.viewmodel

import HomeRecommendData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shanshan.eyepetizer.viewmodel.repository.DiscoveryRepository
import kotlinx.coroutines.flow.Flow

class HomeRecommendViewModel : ViewModel() {

    private val repository by lazy {
        DiscoveryRepository()
    }

    fun getPagingData(): Flow<PagingData<HomeRecommendData.Item>> {
        return repository.getHomeRecommendPagingData().cachedIn(viewModelScope)
    }
}