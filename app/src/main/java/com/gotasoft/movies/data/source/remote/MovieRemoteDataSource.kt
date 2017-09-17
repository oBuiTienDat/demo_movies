package com.gotasoft.movies.data.source.remote

import android.content.Context
import android.util.Log
import com.gotasoft.movies.data.source.MovieDataSource
import com.gotasoft.movies.data.source.remote.api.ApiRemoteDataSource
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by dattien on 9/17/17.
 */

class MovieRemoteDataSource : MovieDataSource {
    internal var subscription: Subscription? = null

    companion object {
        lateinit var mApiRemoteDataSource: ApiRemoteDataSource;

        fun getInstance(context: Context): ApiRemoteDataSource {
            if (mApiRemoteDataSource == null) {
                mApiRemoteDataSource = ApiRemoteDataSource.Factory.create(context)
            }
            return mApiRemoteDataSource
        }
    }

    override fun getMovies(id: String, version: String, lang: String, callback: MovieDataSource.LoadMovieCallback) {
        subscription = mApiRemoteDataSource.getMovie(id, version, lang)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    callback.onMovieLoaded(data)
                    Log.e("DAT", data.toString())
                }, { error ->
                    callback.onDataNotAvailable()
                    Log.e("DAT","1111112222222")
                })
    }

}
