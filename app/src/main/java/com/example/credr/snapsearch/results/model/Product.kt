package com.example.credr.snapsearch.results.model

/**
 * Created by punitdama on 31/12/17.
 */
data class Product(
        val title : String,
        val price : Long,
        val sellingPrice : Long,
        val imgs : List<String>? = null,
        val avgRating : Float)

data class CustomSearchResultDto(val searchResultDTOMobile : SearchResultDTOMobile? = null)

data class SearchResultDTOMobile(val catalogSearchDTOMobile : List<Product>? = null)