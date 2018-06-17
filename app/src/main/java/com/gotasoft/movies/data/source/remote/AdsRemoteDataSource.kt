package com.gotasoft.movies.data.source.remote

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.gotasoft.movies.data.Ads
import com.gotasoft.movies.data.source.AdsDataSource
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by dattien on 6/17/18.
 */
class AdsRemoteDataSource(context: Context) : BaseRemoteDataSource(context), AdsDataSource {
    private var subscription: Subscription? = null

    @SuppressLint("LongLogTag")
    override fun getAds(callback: AdsDataSource.LoadAdsCallback) {
        cancelSubscription()
        subscription = apiRemoteDataSource?.getAds()
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ data ->
                    Log.e("AdsRemoteDataSource", "list ads -> " + Gson().toJson(data)+"/")
                    var ads : Ads
                    ads = Gson().fromJson(Gson().toJson(data).toString(),Ads::class.java)
                    callback.onAdsLoaded(ads)
                }) { error ->
                    Log.e("AdsRemoteDataSource", "ads error -> " + error)
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
