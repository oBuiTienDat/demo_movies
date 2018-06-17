package com.gotasoft.movies.data.source

import com.gotasoft.movies.data.Ads

/**
 * Created by dattien on 6/17/18.
 */
interface AdsDataSource {
    interface LoadAdsCallback {
        fun onAdsLoaded(categorys: Ads)

        fun onDataNotAvailable()
    }

    fun getAds(callback: LoadAdsCallback)

}
