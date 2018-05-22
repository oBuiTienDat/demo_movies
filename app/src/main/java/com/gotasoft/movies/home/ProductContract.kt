package com.gotasoft.movies.home

import com.gotasoft.movies.data.Category
import com.gotasoft.movies.data.Product

/**
 * Created by dattien on 9/24/17.
 */

class ProductContract {
    interface View {
        fun showProduct(products: List<Product>)
        fun showError()
        fun openDetail(product: Product)
        fun loadingProduct(category: Category)
        fun addCategoryView()
        fun addSearchView()
        fun openCategoryView()
        fun openSearchView()
        fun closeCategoryView()
        fun closeSearchView()
        fun onSearch(text: String)

        fun onHome()
        fun onMyMovies()
        fun onHistory()
    }

}
