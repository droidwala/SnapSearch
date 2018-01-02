package com.example.credr.snapsearch.results

import com.bumptech.glide.Glide
import com.example.credr.snapsearch.R
import com.example.credr.snapsearch.databinding.ItemProductBinding
import com.example.credr.snapsearch.results.model.Product
import com.xwray.groupie.Item

/**
 * ViewHolder showing individual product item in Results screen
 * Created by punitdama on 31/12/17.
 */
class ProductItem(private val product : Product) : Item<ItemProductBinding>(){

    override fun getLayout() = R.layout.item_product

    override fun bind(viewBinding: ItemProductBinding, position: Int) {
        Glide.with(viewBinding.root.context)
                .load(product.imgs?.get(0))
                .placeholder(R.drawable.image_placeholder)
                .into(viewBinding.productImage)

        viewBinding.productName.text = product.title
        viewBinding.productPrice.text = viewBinding.root.context.getString(R.string.product_price,product.sellingPrice.toString())
    }



}