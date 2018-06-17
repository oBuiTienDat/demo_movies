package com.gotasoft.movies.home.detail

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import android.widget.Toast
import com.gotasoft.movies.R
import com.gotasoft.movies.data.Detail
import com.gotasoft.movies.data.Product
import com.gotasoft.movies.databinding.ActivityDetailBinding
import android.net.Uri


/**
 * Created by dattien on 9/26/17.
 */

class DetailActivity : AppCompatActivity(),
        DetailContract.View, RadioGroup.OnCheckedChangeListener {

    private lateinit var mActivityDetailBinding: ActivityDetailBinding
    private lateinit var mDetailViewModel: DetailViewModel
    private lateinit var mProduct: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        mActivityDetailBinding.SegmentedGroup.setOnCheckedChangeListener(this)
        mActivityDetailBinding.viewDetail = this
        mDetailViewModel = DetailViewModel(this)
        mActivityDetailBinding.viewModel = mDetailViewModel
        mDetailViewModel.start(this)
        initView()
        mDetailViewModel.addDetail(mProduct)
    }

    private fun initView() {
        mProduct = intent?.getSerializableExtra("EXTRA_PRODUCT") as Product
        mActivityDetailBinding.detail = mProduct
        mDetailViewModel.setProduct(mProduct)
        mActivityDetailBinding.viewBg.alpha = 0.3f
        mActivityDetailBinding.scroll.setOnScrollChangeListener { v: NestedScrollView?,
                                                                  scrollX: Int,
                                                                  scrollY: Int,
                                                                  oldScrollX: Int,
                                                                  oldScrollY: Int ->
            mActivityDetailBinding.viewBg.alpha = getAlphaForView(800 - scrollY)
        }
    }

    private fun getAlphaForView(position: Int): Float {
        var diff = 0
        val minAlpha = 0.3f
        val maxAlpha = 1f
        var alpha = minAlpha
        if (position == 0)
            alpha = minAlpha
        else {
            diff = 800 - position
            alpha += diff * 1f / 800 * (maxAlpha - minAlpha)
        }
        return alpha
    }

    override fun showDetail(detail: Detail) {
    }

    override fun showError() {
        Toast.makeText(this, "Loading Detail error", Toast.LENGTH_SHORT).show()
    }

    override fun onBack() {
        finish()
    }

    override fun onShare() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        val screenshotUri = Uri.parse(mProduct.poster)
        sharingIntent.type = "image/png"
        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri)
        startActivity(Intent.createChooser(sharingIntent, "Share image using"))
    }

    override fun onAdd() {
        mDetailViewModel.updateDetail(mProduct)
    }

    override fun onPlay() {
        var mIntent = Intent(this, CustomYoutubeActivity::class.java)
        mIntent.putExtra("EXTRA_VIDEO_ID", mProduct.trailerId)
        startActivity(mIntent)
    }

    override fun onCheckedChanged(p0: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.btn_infor -> {
                mDetailViewModel.setInfor(mProduct, 1)
            }
            R.id.btn_note -> {
                mDetailViewModel.setInfor(mProduct, 2)
            }
            R.id.btn_ralated -> {
                mDetailViewModel.setInfor(mProduct, 3)
            }
        }
    }
}
