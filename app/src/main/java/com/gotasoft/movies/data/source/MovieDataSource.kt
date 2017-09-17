package com.gotasoft.movies.data.source

import com.gotasoft.movies.data.Movies

/**
 * Created by dattien on 9/17/17.
 */

interface MovieDataSource {
    interface LoadMovieCallback {
        fun onMovieLoaded(movies: List<Movies>)

        fun onDataNotAvailable()
    }

    interface LoadCategoryCallback {

    }
}

