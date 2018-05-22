package com.gotasoft.movies.data.source.remote

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.gotasoft.movies.data.source.CategoryDataSource
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by dattien on 9/23/17.
 */

class CategoryRemoteDataSource(context: Context) : BaseRemoteDataSource(context), CategoryDataSource {
    private var subscription: Subscription? = null

    override fun getCategory(id: String, version: String, lang: String, callback: CategoryDataSource.LoadCategoryCallback) {
        cancelSubscription()
        subscription = apiRemoteDataSource?.getCategories(id, version, lang)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ data ->
                    Log.e("CategoryRemoteDataSource", "list movie -> " + Gson().toJson(data))
                    callback.onCategoryLoaded(data)
                }) { error ->
                    Log.e("CategoryRemoteDataSource", "category error -> " + error)
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

