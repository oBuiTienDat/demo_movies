package com.gotasoft.movies.home.category

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gotasoft.movies.data.Category
import com.gotasoft.movies.databinding.ItemCategoryBinding

/**
 * Created by dattien on 9/24/17.
 */

class CategoryAdapter(mContext: Context, mListLauncher: MutableList<Category>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater: LayoutInflater
    private lateinit var mClickListener: ClickListener
    private lateinit var mCategoryViewHolder: CategoryViewHolder
    private lateinit var mItemCategoryBinding: ItemCategoryBinding
    private var mListCategory: MutableList<Category>

    init {
        inflater = LayoutInflater.from(mContext)
        this.mListCategory = mListLauncher
        mListCategory.addAll(mListLauncher)
        mListCategory.addAll(mListLauncher)
    }

    fun addAllItem(list: MutableList<Category>) {
        list.clear()
        mListCategory.clear()
        mListCategory = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mItemCategoryBinding = ItemCategoryBinding.inflate(inflater, parent, false)
        mCategoryViewHolder = CategoryViewHolder(mItemCategoryBinding)
        return mCategoryViewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val category = mListCategory[position]
        (holder as CategoryViewHolder).itemViewBinding.category = category
    }

    override fun getItemCount(): Int {
        return mListCategory.size
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        mClickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(category: Category)
    }

    inner class CategoryViewHolder(var itemViewBinding: ItemCategoryBinding) :
            RecyclerView.ViewHolder(itemViewBinding.root), View.OnClickListener {

        init {
            itemViewBinding.root.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (mClickListener != null) {
                mClickListener.onItemClick(mListCategory[adapterPosition])
            }
        }
    }
}