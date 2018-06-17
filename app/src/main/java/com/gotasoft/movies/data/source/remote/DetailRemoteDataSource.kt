package com.gotasoft.movies.data.source.remote

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.gotasoft.movies.data.Detail
import com.gotasoft.movies.data.source.DetailDataSource
import com.gotasoft.movies.data.source.local.DetailLocalDataSource
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by dattien on 10/7/17.
 */
class DetailRemoteDataSource(context: Context) : BaseRemoteDataSource(context), DetailDataSource {

    private var subscription: Subscription? = null
    private var detailLocalDataSource: DetailLocalDataSource? = null

    init {
        detailLocalDataSource = DetailLocalDataSource(context)
    }

    override fun getDetail(id: String, callback: DetailDataSource.LoadDetailCallback) {
        cancelSubscription()
        subscription = apiRemoteDataSource?.getDetail(id)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ data ->
                    Log.e("DetailRemoteDataSource", "detail -> " + Gson().toJson(data))
                    var detail = getDetailLocal(data.id)
                    if (detail != null) {
                        data.isAdd = detail.isAdd
                        removeDetailLocal(detail)
                        addDetailLocal(data)
                    }
                    callback.onDetailLoaded(data)
                }) { error ->
                    Log.e("DetailRemoteDataSource", "detail error -> " + error)
                    callback.onDataNotAvailable()
                }
    }

    override fun getDetailLocal(id: String): Detail? {
        return detailLocalDataSource?.getDetail(id)
    }

    override fun addDetailLocal(detail: Detail): Boolean {
        return detailLocalDataSource?.addDetail(detail)!!
    }

    override fun removeDetailLocal(detail: Detail): Boolean {
        detailLocalDataSource?.removeDetail(detail)
        return true
    }

    override fun getListDetailLocal(): List<Detail>? {
        return detailLocalDataSource?.getListDetail()
    }

    fun cancelSubscription() {
        if (subscription != null && !subscription?.isUnsubscribed!!) {
            subscription?.unsubscribe()
        }
        subscription = null
    }
}
