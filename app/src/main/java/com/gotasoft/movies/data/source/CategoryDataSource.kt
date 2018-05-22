package com.gotasoft.movies.data.source

import com.gotasoft.movies.data.Category

/**
 * Created by dattien on 9/23/17.
 */

interface CategoryDataSource {
    interface LoadCategoryCallback {
        fun onCategoryLoaded(categorys: List<Category>)

        fun onDataNotAvailable()
    }

    fun getCategory(id: String, version: String, lang: String, callback: LoadCategoryCallback)

}
