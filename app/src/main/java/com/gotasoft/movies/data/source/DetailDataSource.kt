package com.gotasoft.movies.data.source

import com.gotasoft.movies.data.Detail

/**
 * Created by dattien on 10/7/17.
 */
interface DetailDataSource {
    interface LoadDetailCallback {
        fun onDetailLoaded(detail: Detail)

        fun onDataNotAvailable()
    }

    fun getDetail(id: String, callback: LoadDetailCallback)

    fun getDetailLocal(id: String) : Detail?

    fun getListDetailLocal() : List<Detail>?

    fun addDetailLocal(detail: Detail): Boolean

    fun removeDetailLocal(detail: Detail) : Boolean

}