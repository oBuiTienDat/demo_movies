package com.gotasoft.movies.data.source.remote

import android.content.Context
import android.util.Log

import com.google.gson.Gson
import com.gotasoft.movies.data.source.ProductDataSource

import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by FRAMGIA\bui.tien.dat on 18/09/2017.
 */

class ProductRemoteDataSource (context: Context) : BaseRemoteDataSource(context), ProductDataSource {
    private var subscription: Subscription? = null

    override fun getProduct(id: String, category: String, version: String, lang: String, callback: ProductDataSource.LoadProductCallback) {
        cancelSubscription()
        subscription = apiRemoteDataSource?.getProduct(id, category, version, lang)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ data ->
                    Log.e("ProductRemoteDataSource", "list movie -> " + Gson().toJson(data))
                    callback.onProductLoaded(data)
                }) { error ->
                    Log.e("ProductRemoteDataSource", "movie error -> " + error)
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
