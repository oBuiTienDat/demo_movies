package com.gotasoft.movies.data.source

import android.content.Context
import com.gotasoft.movies.data.Detail
import com.gotasoft.movies.data.source.remote.DetailRemoteDataSource

/**
 * Created by dattien on 10/7/17.
 */
class DetailRepository(context: Context) : DetailDataSource {
    override fun getListDetailLocal(): List<Detail>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val detailRemoteDataSource: DetailRemoteDataSource

    init {
        detailRemoteDataSource = DetailRemoteDataSource(context)
    }

    override fun getDetail(id: String, callback: DetailDataSource.LoadDetailCallback) {
        detailRemoteDataSource.getDetail(id, callback)
    }

    override fun getDetailLocal(id: String): Detail {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addDetailLocal(detail: Detail): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeDetailLocal(detail: Detail): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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