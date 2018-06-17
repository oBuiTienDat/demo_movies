package com.gotasoft.movies.home.detail

import android.content.Context
import android.databinding.BaseObservable
import android.text.TextUtils
import com.android.databinding.library.baseAdapters.BR
import com.gotasoft.movies.R
import com.gotasoft.movies.data.Detail
import com.gotasoft.movies.data.Product
import com.gotasoft.movies.data.source.DetailDataSource
import com.gotasoft.movies.data.source.DetailRepository
import com.gotasoft.movies.data.source.ProductRepository

/**
 * Created by dattien on 10/7/17.
 */
class DetailViewModel(context: Context) : BaseObservable() {
    private val mContext: Context = context
    private lateinit var mDetailRepository: DetailRepository
    private lateinit var mProductRepository: ProductRepository
    private lateinit var mDetailContract: DetailContract.View
    var isLoading: Boolean = false
    var textYourate = "";
    var textImdb = ""
    var textInfor = ""
    var isVisibleAdd: Boolean = true

    fun start(view: DetailContract.View) {
        mDetailContract = view
        mDetailRepository = DetailRepository.getInstance(mContext)
        mProductRepository = ProductRepository.getInstance(mContext)
    }

    fun setProduct(product: Product) {
        textYourate = mContext.getString(R.string.you_rate) + " " + product.imdbRating
        textImdb = mContext.getString(R.string.imdb) + "  " + product.imdbRating
        textInfor = mContext.getString(R.string.director) + "\n" +
                product.director + "\n\n" +
                mContext.getString(R.string.actors) + "\n" +
                product.actors + "\n\n" +
                mContext.getString(R.string.genre) + "\n" +
                product.genre
        reload()
    }

    fun setInfor(product: Product, id: Int) {
        when (id) {
            1 -> textInfor = mContext.getString(R.string.director) + "\n" +
                    product.director + "\n\n" +
                    mContext.getString(R.string.actors) + "\n" +
                    product.actors + "\n\n" +
                    mContext.getString(R.string.genre) + "\n" +
                    product.genre
            2 -> textInfor = product.plot
            3 -> textInfor = ""
        }
        reload()
    }


    fun addDetail(product: Product) {
        var _product: Product? = mProductRepository.getProductLocal(product.id)
        if (_product != null) {
            product.isAdd = _product.isAdd
            mProductRepository.removeProductLocal(_product)
        }
        mProductRepository.addProductLocal(product)
        isVisibleAdd = !(product.isAdd ?: false)
        reload()
    }

    fun updateDetail(product: Product) {
        isVisibleAdd = false
        product.isAdd = true
        mProductRepository.updateProductLocal(product)
        reload()
    }

    fun loadDetail(idProduct: String) {
        if (TextUtils.isEmpty(idProduct)) {
            mDetailContract.showError()
            return
        }
        isLoading = true
        reload()
        mDetailRepository.getDetail(idProduct, object : DetailDataSource.LoadDetailCallback {
            override fun onDataNotAvailable() {
                isLoading = false
                reload()
                mDetailContract.showError()
            }

            override fun onDetailLoaded(detail: Detail) {
                isLoading = false
                reload()
                if (detail == null) {
                    mDetailContract.showError()
                    return
                }
                mDetailContract.showDetail(detail)

            }
        })
    }

    fun reload() {
        notifyPropertyChanged(BR._all)
    }
}