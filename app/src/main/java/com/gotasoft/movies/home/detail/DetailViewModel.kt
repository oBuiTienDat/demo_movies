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

/**
 * Created by dattien on 10/7/17.
 */
class DetailViewModel(context: Context) : BaseObservable() {
    private val mContext: Context = context
    private lateinit var mDetailRepository: DetailRepository
    private lateinit var mDetailContract: DetailContract.View
    var isLoading: Boolean = false
    var textYourate = "";
    var textImdb = ""
    var textInfor = ""

    fun start(view: DetailContract.View) {
        mDetailContract = view
        mDetailRepository = DetailRepository.getInstance(mContext)
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

    fun setInfor(product: Product, id: Int){
        when(id){
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