package com.gotasoft.movies.data.source

import android.content.Context
import com.gotasoft.movies.data.source.remote.DetailRemoteDataSource

/**
 * Created by dattien on 10/7/17.
 */
class DetailRepository(context: Context) : DetailDataSource {

    private val detailRemoteDataSource: DetailRemoteDataSource

    init {
        detailRemoteDataSource = DetailRemoteDataSource(context)
    }

    override fun getDetail(id: String, callback: DetailDataSource.LoadDetailCallback) {
        detailRemoteDataSource.getDetail(id, callback)
    }

    companion object {
        private var detailRepository: DetailRepository? = null
        fun getInstance(context: Context): DetailRepository {
            if (detailRepository == null) {
                detailRepository = DetailRepository(context)
            }
            return detailRepository as DetailRepository
        }
    }
}