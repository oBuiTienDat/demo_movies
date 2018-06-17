package com.gotasoft.movies.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gotasoft.movies.R
import com.gotasoft.movies.data.Ads
import com.gotasoft.movies.data.source.AdsDataSource
import com.gotasoft.movies.data.source.AdsRepository
import com.gotasoft.movies.home.ProductActivity
import com.gotasoft.movies.util.AppConstants


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val mHandler = Handler(Looper.myLooper())
        AdsRepository.getInstance(this).getAds(object :AdsDataSource.LoadAdsCallback{
            override fun onAdsLoaded(categorys: Ads) {
                AppConstants.ads = categorys
                mHandler.postDelayed({ openHome() }, 2000)
            }

            override fun onDataNotAvailable() {
                mHandler.postDelayed({ openHome() }, 2000)
            }
        })


    }

    private fun openHome() {
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
        finish()
    }
}
