package com.shanshan.eyepetizer.pagingdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pax.data.Movie
import kotlinx.coroutines.flow.Flow

class MovieViewModel : ViewModel() {

    private val movies by lazy {
        Pager(
            config = PagingConfig(pageSize = 8, prefetchDistance = 8, initialLoadSize = 16),
            pagingSourceFactory = { MoviePagingSource() }
        ).flow
            .cachedIn(viewModelScope)
    }

    fun loadMovies(): Flow<PagingData<Movie>> = movies
}