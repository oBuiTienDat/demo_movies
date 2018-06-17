package com.gotasoft.movies.data.source

import android.content.Context
import com.gotasoft.movies.data.source.remote.AdsRemoteDataSource

/**
 * Created by dattien on 6/17/18.
 */
class AdsRepository(context: Context) : AdsDataSource {

    private val adsRemoteDataSource: AdsRemoteDataSource

    init {
        adsRemoteDataSource = AdsRemoteDataSource(context)
    }

    override fun getAds(callback: AdsDataSource.LoadAdsCallback) {
        adsRemoteDataSource.getAds(callback)
    }

    companion object {
        private var adsRepository: AdsRepository? = null
        fun getInstance(context: Context): AdsRepository {
            if (adsRepository == null) {
                adsRepository = AdsRepository(context)
            }
            return adsRepository as AdsRepository
        }
    }
}
