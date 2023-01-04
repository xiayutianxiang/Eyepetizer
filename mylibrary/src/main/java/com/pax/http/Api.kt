package com.pax.http

import com.pax.data.Movie
import com.pax.data.Movies

interface Api {
    fun getMovies(page:Int,size:Int) : Movies
}