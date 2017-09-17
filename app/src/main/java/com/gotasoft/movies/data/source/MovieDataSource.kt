package com.gotasoft.movies.data.source

import com.gotasoft.movies.data.Movie

/**
 * Created by dattien on 9/17/17.
 */

interface MovieDataSource {
    interface LoadMovieCallback {
        fun onMovieLoaded(movies: List<Movie>)

        fun onDataNotAvailable()
    }

    fun getMovies(id: String, version: String, lang: String, callback: LoadMovieCallback)
}

