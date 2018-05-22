package com.gotasoft.movies.data.source.remote

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.gotasoft.movies.data.source.DetailDataSource
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by dattien on 10/7/17.
 */
class DetailRemoteDataSource(context: Context) : BaseRemoteDataSource(context), DetailDataSource {
    private var subscription: Subscription? = null

    override fun getDetail(id: String, callback: DetailDataSource.LoadDetailCallback) {
        cancelSubscription()
        subscription = apiRemoteDataSource?.getDetail(id)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ data ->
                    Log.e("DetailRemoteDataSource", "detail -> " + Gson().toJson(data))
                    callback.onDetailLoaded(data)
                }) { error ->
                    Log.e("DetailRemoteDataSource", "detail error -> " + error)
                    callback.onDataNotAvailable()
                }
    }

    fun cancelSubscription() {
        if (subscription != null && !subscription!!.isUnsubscribed) {
            subscription!!.unsubscribe()
        }
        subscription = null
    }
}
