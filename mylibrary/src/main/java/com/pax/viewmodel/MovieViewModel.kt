package com.pax.viewmodel
import com.pax.data.Movie
import com.pax.pagedatasource.MovieDataSource
import com.pax.pagedatasource.MovieDataSourceFactory

class MovieViewModel{//设置当距离底部还有多少条数据时开始加载下一页
//设置首次加载的数量
    /*() : ViewModel() {

    var moviePagedList: LiveData<PagedList<Movie>>? = null

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MovieDataSource.PER_PAGE) //设置当距离底部还有多少条数据时开始加载下一页
            .setPrefetchDistance(2) //设置首次加载的数量
            .setInitialLoadSizeHint(MovieDataSource.PER_PAGE * 2)
            .setMaxSize(65536 * MovieDataSource.PER_PAGE)
            .build()
        moviePagedList = LivePagedListBuilder(MovieDataSourceFactory(), config).build()
    }*/
}