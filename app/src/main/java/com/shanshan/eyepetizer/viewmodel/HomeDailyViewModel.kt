package com.shanshan.eyepetizer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shanshan.eyepetizer.data.HomeDailyData
import com.shanshan.eyepetizer.data.repository.HomePageRepository
import kotlinx.coroutines.flow.Flow

class HomeDailyViewModel : ViewModel() {

    private val repository by lazy {
        HomePageRepository()
    }

    fun getDailyPagingData(): Flow<PagingData<HomeDailyData.Item>> =
        repository.getHomeDailyPagingData().cachedIn(viewModelScope)
}