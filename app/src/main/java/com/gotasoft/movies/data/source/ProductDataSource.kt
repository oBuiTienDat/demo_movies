package com.gotasoft.movies.data.source

import com.gotasoft.movies.data.Detail
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

    fun searchProduct(id: String, category: String, version: String, lang: String, text: String, callback: LoadProductCallback)

    fun getProductLocal(id: String) : Product?

    fun getListProductLocal() : List<Product>?

    fun getListAddProductLocal() : List<Product>?

    fun addProductLocal(detail: Product)

    fun updateProductLocal(detail: Product)

    fun removeProductLocal(detail: Product)
}

