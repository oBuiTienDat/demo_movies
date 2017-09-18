package com.gotasoft.movies.data.source.local

import android.content.Context
import com.gotasoft.movies.data.Product
import com.gotasoft.movies.data.ProductDao
import org.greenrobot.greendao.DaoException

/**
 * Created by dattien on 9/17/17.
 */
class ProductLocalDataSource(context: Context) {

    var mProductDao: ProductDao? = null

    init {
        mProductDao = LocalDatabaseHelper.getInstance(context).daoSession.productDao
    }

    fun addProduct(product: Product) {
        mProductDao?.insert(product)
    }

    fun updateProduct(product: Product) {
        mProductDao?.update(product)
    }

    fun getListProduct(categoryId: String): List<Product>? {
        try {
            return mProductDao?.queryBuilder()?.orderDesc(ProductDao.Properties.CategoryId) as List<Product>
        } catch (e: DaoException) {
            e.printStackTrace()
            return null
        }
    }
}