package com.gotasoft.movies.home.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gotasoft.movies.databinding.FragmentSearchBinding
import com.gotasoft.movies.home.ProductActivity


/**
 * Created by dattien on 9/24/17.
 */
class SearchFragment : Fragment() {
    private lateinit var mFragmentSearchBinding: FragmentSearchBinding
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        initView()
        return mFragmentSearchBinding.root
    }

    companion object {
        fun newInstance() = SearchFragment()
    }

    private fun initView() {
        mFragmentSearchBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                (context as ProductActivity).onSearch(s)
                mFragmentSearchBinding.searchView.setQuery("", false)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })

    }

}