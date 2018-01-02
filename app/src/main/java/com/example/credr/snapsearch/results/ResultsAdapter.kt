package com.example.credr.snapsearch.results

import com.example.credr.snapsearch.results.model.Product
import com.xwray.groupie.GroupAdapter

/**
 * Recyclerview Adapter showing list of Products
 * Created by punitdama on 31/12/17.
 */
class ResultsAdapter : GroupAdapter(){


    fun addProducts(products : List<Product>){
        for(product in products){
            add(ProductItem(product))
        }
    }
}