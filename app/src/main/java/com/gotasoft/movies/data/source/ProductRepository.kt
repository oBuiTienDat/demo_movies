package com.gotasoft.movies.data.source

import android.content.Context

import com.gotasoft.movies.data.source.remote.ProductRemoteDataSource

/**
 * Created by FRAMGIA\bui.tien.dat on 18/09/2017.
 */

class ProductRepository private constructor(context: Context) : ProductDataSource {

    private val productRemoteDataSource: ProductRemoteDataSource

    init {
        productRemoteDataSource = ProductRemoteDataSource(context)
    }

    override fun getProduct(id: String, category: String,
                            version: String, lang: String,
                            callback: ProductDataSource.LoadProductCallback) {
        productRemoteDataSource.getProduct(id, category, version, lang, callback)
    }

    companion object {
        private var productRepository: ProductRepository? = null
        fun getInstance(context: Context): ProductRepository {
            if (productRepository == null) {
                productRepository = ProductRepository(context)
            }
            return productRepository as ProductRepository
        }
    }
}
