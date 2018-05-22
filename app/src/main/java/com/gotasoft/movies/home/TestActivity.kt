package com.gotasoft.movies.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.google.android.youtube.player.internal.l
import com.gotasoft.movies.R
import android.databinding.adapters.TextViewBindingAdapter.setText




@Suppress("DEPRECATION")
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        //var mBinding: ActivityTestBinding = DataBindingUtil.setContentView(this, R.layout.activity_test)
        val mobileArray = arrayOf("Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu")

        val adapter = ArrayAdapter(this,
                R.layout.test, mobileArray)
        var l1 = findViewById(R.id.lv1) as ListView
        var l2 = findViewById(R.id.lv2) as ListView
        var md = findViewById(R.id.drawer123) as SlidingDrawer
        var V1 = findViewById(R.id.v1) as View
        var V2 = findViewById(R.id.v2) as View
        var btn = findViewById(R.id.btn_test) as Button
        var edt = findViewById(R.id.edt) as EditText
        l1.adapter = adapter
        l2.adapter = adapter

        l1.setOnTouchListener({ v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (md.isOpened) {
                    md.animateClose()
                }
                true
            } else false
        })

        btn.setOnClickListener {

        }

        md.setOnDrawerScrollListener(object : SlidingDrawer.OnDrawerScrollListener {
            override fun onScrollStarted() {
                Log.e("DAT", "start -======")
                //V1.visibility = View.VISIBLE
            }

            override fun onScrollEnded() {
                Log.e("DAT", "stop -======")
            }
        })

        md.setOnDrawerOpenListener(SlidingDrawer.OnDrawerOpenListener {
            Log.e("DAT", "open 1111111")
        })

        md.setOnDrawerCloseListener(SlidingDrawer.OnDrawerCloseListener {
            Log.e("DAT", "close 222222")
            V1.visibility = View.GONE
        })
    }
}
