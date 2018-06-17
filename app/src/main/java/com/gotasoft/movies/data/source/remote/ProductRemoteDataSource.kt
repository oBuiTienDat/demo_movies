package com.gotasoft.movies.data.source.remote

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

import com.google.gson.Gson
import com.gotasoft.movies.data.Product
import com.gotasoft.movies.data.source.ProductDataSource
import com.gotasoft.movies.data.source.local.ProductLocalDataSource

import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by FRAMGIA\bui.tien.dat on 18/09/2017.
 */

class ProductRemoteDataSource(context: Context) : BaseRemoteDataSource(context), ProductDataSource {

    private var productLocalDataSource: ProductLocalDataSource

    init {
        productLocalDataSource = ProductLocalDataSource(context)
    }

    private var subscription: Subscription? = null

    override fun getProduct(id: String, category: String, version: String, lang: String, callback: ProductDataSource.LoadProductCallback) {
        cancelSubscription()
        subscription = apiRemoteDataSource?.getProduct(id, category, version, lang)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ data ->
                    Log.e("ProductRemoteDataSource", "list movie -> " + Gson().toJson(data))
                    callback.onProductLoaded(data)
                }) { error ->
                    Log.e("ProductRemoteDataSource", "movie error -> " + error)
                    callback.onDataNotAvailable()
                }
    }

    @SuppressLint("LongLogTag")
    override fun searchProduct(id: String, category: String, version: String, lang: String, text: String, callback: ProductDataSource.LoadProductCallback) {
        cancelSubscription()
        subscription = apiRemoteDataSource?.searchProduct(id, category, version, lang, text)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ data ->
                    Log.e("ProductRemoteDataSource Search", "list movie -> " + Gson().toJson(data))
                    callback.onProductLoaded(data)
                }) { error ->
                    Log.e("ProductRemoteDataSource Search", "movie error -> " + error)
                    callback.onDataNotAvailable()
                }
    }

    override fun getProductLocal(id: String): Product? {
        return productLocalDataSource.getProduct(id)
    }

    override fun getListProductLocal(): List<Product>? {
        return productLocalDataSource.getListProduct()
    }

    override fun addProductLocal(detail: Product) {
        productLocalDataSource.addProduct(detail)
    }

    override fun removeProductLocal(detail: Product) {
        productLocalDataSource.removeProduct(detail)
    }

    override fun updateProductLocal(detail: Product) {
        productLocalDataSource.updateProduct(detail)
    }

    override fun getListAddProductLocal(): List<Product>? {
        return productLocalDataSource.getListAddProduct()
    }

    fun cancelSubscription() {
        if (subscription != null && !subscription!!.isUnsubscribed) {
            subscription!!.unsubscribe()
        }
        subscription = null
    }
}
