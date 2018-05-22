package com.gotasoft.movies

import android.app.Application
import android.support.multidex.MultiDexApplication

/**
 * Created by dattien on 9/17/17.
 */

class App : MultiDexApplication() {

    override fun onCreate() {
        mApplication = this
        super.onCreate()
    }


    companion object {
        private lateinit var mApplication: MultiDexApplication
        fun getInstance(): Application? {
            return mApplication
        }
    }


}
