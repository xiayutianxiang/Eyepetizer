package com.shanshan.eyepetizer.pagingdemo

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pax.data.Movie
import com.pax.http.RetrofitClient
import kotlinx.coroutines.delay
import java.lang.Exception

class MoviePagingSource : PagingSource<Int, Movie>() {

    companion object {
        private const val TAG = "MoviePagingSource"
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        delay(2000)
        val currentPage = params.key ?: 1
        val pageSize = params.loadSize
        val movies = RetrofitClient.getInstance().getApi().getMovies(currentPage, pageSize)
        Log.d(TAG, "currentPage ---> $currentPage, pageSize ---> $pageSize")

        var preKey: Int? = null
        var nextKey: Int? = null

        val realPageSize = 8
        val initialLoadSize = 16
        if (currentPage == 1) {
            preKey = null
            nextKey = initialLoadSize / realPageSize + 1
        }

        return try {
            LoadResult.Page(
                data = movies.movieList!!,
                prevKey = preKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }
}