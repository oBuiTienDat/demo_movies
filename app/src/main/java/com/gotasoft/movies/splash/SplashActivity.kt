package com.gotasoft.movies.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.gotasoft.movies.R
import com.gotasoft.movies.data.source.remote.MovieRemoteDataSource

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        MovieRemoteDataSource.getInstance(this).getMovie("phimhd_v3","20170101","en")
    }
}
