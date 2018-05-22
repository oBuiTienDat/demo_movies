package com.gotasoft.movies.home.category

import android.content.Context
import android.databinding.BaseObservable
import com.android.databinding.library.baseAdapters.BR
import com.gotasoft.movies.R
import com.gotasoft.movies.data.Category
import com.gotasoft.movies.data.source.CategoryDataSource
import com.gotasoft.movies.data.source.CategoryRepository

/**
 * Created by dattien on 9/23/17.
 */

class CategoryViewModel(context: Context) : BaseObservable() {

    private val mContext: Context = context
    private lateinit var mCategoryRepository: CategoryRepository
    private lateinit var mCategoryContract: CategoryContract.View
    var isLoading: Boolean = false

    companion object {
        lateinit var mCategory: Category
    }

    fun start(view: CategoryContract.View) {
        mCategoryContract = view
        mCategoryRepository = CategoryRepository.getInstance(mContext)
        loadCategory()
    }

    fun loadCategory() {
        isLoading = true
        reload()
        val id = mContext.getString(R.string.id);
        val version = mContext.getString(R.string.version)
        val lang = mContext.getString(R.string.lang)
        mCategoryRepository.getCategory(id, version, lang, object : CategoryDataSource.LoadCategoryCallback {
            override fun onCategoryLoaded(categorys: List<Category>) {
                isLoading = false
                reload()
                if (categorys == null) {
                    mCategoryContract.showError()
                    return
                }
                if (categorys.size > 0) {
                    mCategory = categorys[0]
                }
                mCategoryContract.showCategory(categorys)
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
