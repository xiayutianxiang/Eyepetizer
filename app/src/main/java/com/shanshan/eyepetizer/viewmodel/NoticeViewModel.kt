package com.shanshan.eyepetizer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shanshan.eyepetizer.data.NoticePushData
import com.shanshan.eyepetizer.data.repository.NoticePagePushRepository
import kotlinx.coroutines.flow.Flow

class NoticeViewModel : ViewModel() {

    private val repository by lazy {
        NoticePagePushRepository()
    }

    fun getNoticePushContent(): Flow<PagingData<NoticePushData.Message>> =
        repository.getNoticePushPagingData().cachedIn(viewModelScope)
}