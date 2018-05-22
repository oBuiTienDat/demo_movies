package com.gotasoft.movies.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gotasoft.movies.data.Product
import com.gotasoft.movies.databinding.ItemProductBinding

/**
 * Created by dattien on 9/24/17.
 */

class ProductAdapter(mContext: Context, mListLauncher: MutableList<Product>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater: LayoutInflater
    private lateinit var mClickListener: ClickListener
    private lateinit var mProductViewHolder: ProductViewHolder
    private lateinit var mItemProductBinding: ItemProductBinding
    private var mListProduct: MutableList<Product>

    init {
        inflater = LayoutInflater.from(mContext)
        this.mListProduct = mListLauncher
    }

    fun addAllItem(list: MutableList<Product>) {
        list.clear()
        mListProduct.clear()
        mListProduct = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mItemProductBinding = ItemProductBinding.inflate(inflater, parent, false)
        mProductViewHolder = ProductViewHolder(mItemProductBinding)
        return mProductViewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = mListProduct[position]
        (holder as ProductViewHolder).itemViewBinding.product = product
    }

    override fun getItemCount(): Int {
        return mListProduct.size
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        mClickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(product: Product)
    }

    inner class ProductViewHolder(var itemViewBinding: ItemProductBinding) :
            RecyclerView.ViewHolder(itemViewBinding.root), View.OnClickListener {

        init {
            itemViewBinding.root.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (mClickListener != null) {
                mClickListener.onItemClick(mListProduct[adapterPosition])
            }
        }
    }
}
