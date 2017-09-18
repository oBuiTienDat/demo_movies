package com.gotasoft.movies.data.source.remote

import android.content.Context

import com.gotasoft.movies.data.source.remote.api.ApiRemoteDataSource

/**
 * Created by FRAMGIA\bui.tien.dat on 18/09/2017.
 */

open class BaseRemoteDataSource(context: Context) {
    var apiRemoteDataSource: ApiRemoteDataSource? = null

    init {
        apiRemoteDataSource = ApiRemoteDataSource.Factory.create(context)
    }
}