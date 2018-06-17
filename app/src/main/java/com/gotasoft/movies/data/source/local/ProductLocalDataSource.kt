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

    fun removeProduct(product: Product) {
        mProductDao?.delete(product)
    }

    fun getListProduct(): List<Product>? {
        try {
            return mProductDao?.queryBuilder()?.orderDesc(ProductDao.Properties.Id)?.list()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun getListAddProduct(): List<Product>? {
        try {
            return mProductDao?.queryBuilder()?.where(ProductDao.Properties.IsAdd.eq(true))?.list()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun getProduct(categoryId: String): Product? {
        try {
            return mProductDao?.queryBuilder()?.where(ProductDao.Properties.Id.eq(categoryId))?.list()?.get(0)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}