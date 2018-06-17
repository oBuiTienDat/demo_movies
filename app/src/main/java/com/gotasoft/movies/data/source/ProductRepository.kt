package com.gotasoft.movies.data.source

import android.content.Context
import com.gotasoft.movies.data.Product

import com.gotasoft.movies.data.source.remote.ProductRemoteDataSource

/**
 * Created by FRAMGIA\bui.tien.dat on 18/09/2017.
 */

class ProductRepository(context: Context) : ProductDataSource {

    private val productRemoteDataSource: ProductRemoteDataSource

    init {
        productRemoteDataSource = ProductRemoteDataSource(context)
    }

    override fun getProduct(id: String, category: String,
                            version: String, lang: String,
                            callback: ProductDataSource.LoadProductCallback) {
        productRemoteDataSource.getProduct(id, category, version, lang, callback)
    }

    override fun searchProduct(id: String, category: String,
                               version: String, lang: String, text: String,
                               callback: ProductDataSource.LoadProductCallback) {
        productRemoteDataSource.searchProduct(id, category, version, lang, text, callback)
    }

    override fun getProductLocal(id: String): Product? {
        return productRemoteDataSource.getProductLocal(id)
    }

    override fun getListProductLocal(): List<Product>? {
        return productRemoteDataSource.getListProductLocal()
    }

    override fun addProductLocal(detail: Product) {
        productRemoteDataSource.addProductLocal(detail)
    }

    override fun removeProductLocal(detail: Product) {
        productRemoteDataSource.removeProductLocal(detail)
    }

    override fun updateProductLocal(detail: Product) {
        productRemoteDataSource.updateProductLocal(detail)
    }

    override fun getListAddProductLocal(): List<Product>? {
        return productRemoteDataSource.getListAddProductLocal()
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
