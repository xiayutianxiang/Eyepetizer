package com.shanshan.eyepetizer.viewmodel

import HomeRecommendData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shanshan.eyepetizer.data.repository.HomePageRepository
import kotlinx.coroutines.flow.Flow

class HomeRecommendViewModel : ViewModel() {

    private val repository by lazy {
        HomePageRepository()
    }

    fun getPagingData(): Flow<PagingData<HomeRecommendData.Item>> {
        return repository.getHomeRecommendPagingData().cachedIn(viewModelScope)
    }
}