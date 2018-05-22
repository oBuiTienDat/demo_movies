package com.gotasoft.movies.home.category

import com.gotasoft.movies.data.Category

/**
 * Created by dattien on 9/23/17.
 */

class CategoryContract {
    interface View {
        fun showCategory(categorys: List<Category>)
        fun showError()
        fun openProduct(category: Category)
    }
}
