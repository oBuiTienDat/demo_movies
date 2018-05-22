package com.gotasoft.movies.data.source

import android.content.Context
import com.gotasoft.movies.data.source.remote.CategoryRemoteDataSource

/**
 * Created by dattien on 9/23/17.
 */

class CategoryRepository(context: Context) : CategoryDataSource {

    private val categoryRemoteDataSource: CategoryRemoteDataSource

    init {
        categoryRemoteDataSource = CategoryRemoteDataSource(context)
    }

    override fun getCategory(id: String,
                             version: String, lang: String,
                             callback: CategoryDataSource.LoadCategoryCallback) {
        categoryRemoteDataSource.getCategory(id, version, lang, callback)
    }

    companion object {
        private var categoryRepository: CategoryRepository? = null
        fun getInstance(context: Context): CategoryRepository {
            if (categoryRepository == null) {
                categoryRepository = CategoryRepository(context)
            }
            return categoryRepository as CategoryRepository
        }
    }
}

