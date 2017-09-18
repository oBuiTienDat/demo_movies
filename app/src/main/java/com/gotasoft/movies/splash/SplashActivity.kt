package com.gotasoft.movies.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.gotasoft.movies.R
import com.gotasoft.movies.data.Category
import com.gotasoft.movies.data.Product
import com.gotasoft.movies.data.source.ProductDataSource
import com.gotasoft.movies.data.source.remote.ProductRemoteDataSource

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}
