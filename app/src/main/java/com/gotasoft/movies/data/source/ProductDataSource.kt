package com.gotasoft.movies.data.source

import com.gotasoft.movies.data.Product

/**
 * Created by dattien on 9/17/17.
 */

interface ProductDataSource {
    interface LoadProductCallback {
        fun onProductLoaded(product: List<Product>)

        fun onDataNotAvailable()
    }

    fun getProduct(id: String, category: String, version: String, lang: String, callback: LoadProductCallback)
}

