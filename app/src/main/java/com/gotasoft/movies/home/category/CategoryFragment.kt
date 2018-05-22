package com.gotasoft.movies.home.category

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gotasoft.movies.data.Category
import com.gotasoft.movies.databinding.FragmentCategoryBinding
import com.gotasoft.movies.home.ProductActivity

/**
 * Created by dattien on 9/23/17.
 */

class CategoryFragment : Fragment(), CategoryContract.View {


    private lateinit var mCategoryViewModel: CategoryViewModel
    private lateinit var mCategoryAdapter: CategoryAdapter
    private lateinit var mFragmentCategoryBinding: FragmentCategoryBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentCategoryBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        initView()
        return mFragmentCategoryBinding.root
    }

    private fun initView() {
        mCategoryViewModel = CategoryViewModel(context)
        mCategoryViewModel.start(this)
        mFragmentCategoryBinding.viewmodel = mCategoryViewModel
    }

    companion object {
        fun newInstance() = CategoryFragment()
    }

    override fun showCategory(categorys: List<Category>) {
        mCategoryAdapter = CategoryAdapter(context, categorys as MutableList<Category>)
        mFragmentCategoryBinding.rcvCategory.layoutManager = LinearLayoutManager(context)
        mFragmentCategoryBinding.rcvCategory.adapter = mCategoryAdapter
        mCategoryAdapter.setOnItemClickListener(object : CategoryAdapter.ClickListener {
            override fun onItemClick(category: Category) {
                openProduct(category)
            }
        })
        openProduct(categorys[0])
    }

    override fun showError() {
        Toast.makeText(context, "Loading Category error", Toast.LENGTH_SHORT).show()
    }

    override fun openProduct(category: Category) {
        (activity as ProductActivity).loadingProduct(category)
    }

}
