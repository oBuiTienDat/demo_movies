package com.gotasoft.movies.home

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Toast
import com.gotasoft.movies.R
import com.gotasoft.movies.data.Category
import com.gotasoft.movies.data.Product
import com.gotasoft.movies.databinding.ActivityHomeBinding
import com.gotasoft.movies.home.category.CategoryFragment
import com.gotasoft.movies.home.category.CategoryViewModel
import com.gotasoft.movies.home.detail.DetailActivity
import com.gotasoft.movies.home.search.SearchFragment


@Suppress("DEPRECATION")
class ProductActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private var lastTranslate = 0.0f
    private var scroll = 1

    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var mProductAdapter: ProductAdapter
    private lateinit var mActivityHomeBinding: ActivityHomeBinding
    private lateinit var mCategory: Category
    private lateinit var mLinearLayoutManager:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        initView()
        addSearchView()
        addCategoryView()
    }

    private fun initView() {
        mDrawerToggle = object : ActionBarDrawerToggle(this, mActivityHomeBinding.drawerLayout, null, R.string.app_name, R.string.app_name) {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val moveFactor = mActivityHomeBinding.frameLeft.getWidth() * slideOffset * scroll

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    mActivityHomeBinding.frameContainer.translationX = moveFactor
                } else {
                    val anim = TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f)
                    anim.duration = 0
                    anim.fillAfter = true
                    mActivityHomeBinding.frameContainer.startAnimation(anim)
                    lastTranslate = moveFactor
                }
            }

            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
                scroll = 1
            }
        }

        mActivityHomeBinding.drawerLayout.setDrawerListener(mDrawerToggle)
        mActivityHomeBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, findViewById(R.id.frame_right))

        mProductViewModel = ProductViewModel(this)
        mProductViewModel.start(this)
        mActivityHomeBinding.viewmodel = mProductViewModel
        mActivityHomeBinding.viewaction = this
    }

    override fun showProduct(products: List<Product>) {
        mProductAdapter = ProductAdapter(this, products as MutableList<Product>)
        mLinearLayoutManager = LinearLayoutManager(this)
        mActivityHomeBinding.rcvProduct.layoutManager = mLinearLayoutManager
        mActivityHomeBinding.rcvProduct.adapter = mProductAdapter
        mProductAdapter.setOnItemClickListener(object : ProductAdapter.ClickListener {
            override fun onItemClick(product: Product) {
                openDetail(product)
            }
        })
    }

    override fun showError() {
        Toast.makeText(this, "Loading Product error", Toast.LENGTH_SHORT).show()
    }

    override fun openDetail(product: Product) {
        var mIntent = Intent(this, DetailActivity::class.java)
        mIntent.putExtra("EXTRA_PRODUCT", product)
        startActivity(mIntent)
    }

    override fun loadingProduct(category: Category) {
        mCategory = category
        mProductViewModel.loadProduct(category)
        closeCategoryView()
    }

    override fun openCategoryView() {
        scroll = 1
        mActivityHomeBinding.drawerLayout.openDrawer(mActivityHomeBinding.frameLeft)
    }

    override fun openSearchView() {
        scroll = -1
        mActivityHomeBinding.drawerLayout.openDrawer(mActivityHomeBinding.frameRight)
    }

    override fun closeCategoryView() {
        mActivityHomeBinding.drawerLayout.closeDrawer(mActivityHomeBinding.frameLeft)
    }

    override fun closeSearchView() {
        mActivityHomeBinding.drawerLayout.closeDrawer(mActivityHomeBinding.frameRight)
    }

    override fun addCategoryView() {
        var fragmentCategory: Fragment = CategoryFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_left, fragmentCategory)
                .commit()
    }

    override fun addSearchView() {
        var fragmentCategory: Fragment = SearchFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_right, fragmentCategory)
                .commit()
    }

    override fun onSearch(text: String) {
        closeSearchView()
        mProductViewModel.searchProduct(mCategory, text)
    }

    override fun onHome() {
        if (CategoryViewModel.mCategory != null) {
            mProductViewModel.loadProduct(CategoryViewModel.mCategory)
        }
    }

    override fun onMyMovies() {
    }

    override fun onHistory() {
    }
}
