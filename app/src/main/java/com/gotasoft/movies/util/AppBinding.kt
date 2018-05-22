package com.gotasoft.movies.util

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.gotasoft.movies.App
import com.gotasoft.movies.R

/**
 * Created by dattien on 9/24/17.
 */

object AppBinding {

    @BindingAdapter("app:backgroundRoundUrl")
    @JvmStatic
    fun setBackgroundRoundUrl(imageView: ImageView, url: String) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        Glide.with(App.getInstance())
                .load(url)
                .asBitmap()
                .error(R.color.colorDriver)
                .centerCrop()
                .into(object : BitmapImageViewTarget(imageView) {
                    override fun setResource(resource: Bitmap) {
                        val circularBitmapDrawable = RoundedBitmapDrawableFactory
                                .create(App.getInstance()?.resources, resource)
                        circularBitmapDrawable.isCircular = true
                        imageView.setImageDrawable(circularBitmapDrawable)
                    }
                })
    }

    @BindingAdapter("backgroundUrl")
    @JvmStatic
    fun setBackgroundUrl(imageView: ImageView, url: String) {
        if (TextUtils.isEmpty(url)) {
            return
        }

        Glide.with(App.getInstance())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.color.colorDriver)
                .into(imageView)
    }
}
