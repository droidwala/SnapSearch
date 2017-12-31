package com.example.credr.snapsearch.search.model

/**
 * Created by punitdama on 31/12/17.
 */
data class SearchActivityViewState(
        val isLoading : Boolean = false,
        val error : String? = null,
        val autosuggestions : List<AutoSuggestion>? = null
)