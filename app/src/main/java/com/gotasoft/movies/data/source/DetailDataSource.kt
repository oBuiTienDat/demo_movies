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

}