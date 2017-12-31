package com.example.credr.snapsearch.results.model

/**
 * Created by punitdama on 31/12/17.
 */
data class Product(
        var title : String,
        var price : Long,
        var sellingPrice : Long,
        var imgs : List<String>? = null,
        var avgRating : Float)

data class CustomSearchResultDto(var searchResultDTOMobile : SearchResultDTOMobile? = null)

data class SearchResultDTOMobile(var catalogSearchDTOMobile : List<Product>? = null)