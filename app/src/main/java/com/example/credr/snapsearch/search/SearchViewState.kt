package com.example.credr.snapsearch.search

import com.example.credr.snapsearch.search.model.AutoSuggestion

/**
 * Created by punitdama on 31/12/17.
 */
data class SearchViewState(
        val isLoading : Boolean = false,
        val error : String? = null,
        val autosuggestions : List<AutoSuggestion>? = null
)