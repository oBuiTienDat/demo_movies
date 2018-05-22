package com.gotasoft.movies.home

import android.content.Context
import android.databinding.BaseObservable
import com.android.databinding.library.baseAdapters.BR
import com.gotasoft.movies.R
import com.gotasoft.movies.data.Category
import com.gotasoft.movies.data.Product
import com.gotasoft.movies.data.source.ProductDataSource
import com.gotasoft.movies.data.source.ProductRepository

/**
 * Created by dattien on 9/24/17.
 */
class ProductViewModel(context: Context) : BaseObservable() {

    private val mContext: Context = context
    private lateinit var mProductRepository: ProductRepository
    private lateinit var mProductContract: ProductContract.View
    var isLoading: Boolean = false
    var title: String = ""

    fun start(view: ProductContract.View) {
        mProductContract = view
        mProductRepository = ProductRepository.getInstance(mContext)
    }

    fun loadProduct(category: Category) {
        title = category.name
        isLoading = true
        reload()
        val id = mContext.getString(R.string.id);
        val version = mContext.getString(R.string.version)
        val lang = mContext.getString(R.string.lang)
        mProductRepository.getProduct(id, category.id, version, lang, object : ProductDataSource.LoadProductCallback {
            override fun onProductLoaded(products: List<Product>) {
                isLoading = false
                reload()
                if (products == null) {
                    mProductContract.showError()
                    return
                }
                mProductContract.showProduct(products)
            }

            override fun onDataNotAvailable() {
                isLoading = false
                reload()
            }
        })
    }

    fun searchProduct(category: Category, text: String) {
        isLoading = true
        reload()
        val id = mContext.getString(R.string.id);
        val version = mContext.getString(R.string.version)
        val lang = mContext.getString(R.string.lang)
        mProductRepository.searchProduct(id, category.id, version, lang, text, object : ProductDataSource.LoadProductCallback {
            override fun onProductLoaded(products: List<Product>) {
                isLoading = false
                reload()
                if (products == null) {
                    mProductContract.showError()
                    return
                }
                mProductContract.showProduct(products)
            }

            override fun onDataNotAvailable() {
                isLoading = false
                reload()
            }
        })
    }

    fun reload() {
        notifyPropertyChanged(BR._all)
    }

}