package com.gotasoft.movies.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import com.gotasoft.movies.R
import com.gotasoft.movies.home.ProductActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val mHandler = Handler(Looper.myLooper())
        mHandler.postDelayed({ openHome() }, 3000)
    }

    private fun openHome() {
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
        finish()
    }
}
