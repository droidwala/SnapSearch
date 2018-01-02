package com.example.credr.snapsearch.results

import com.example.credr.snapsearch.results.model.Product

/**
 * Data class representing various states of Results screen
 * Created by punitdama on 31/12/17.
 */
data class ResultsViewState(
        val isLoading : Boolean = false,
        val error : String? = null,
        val products : List<Product>? = null
)