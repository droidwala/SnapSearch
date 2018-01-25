package com.example.credr.snapsearch.results.model

/**
 * Created by punitdama on 31/12/17.
 */
data class SearchResultsResponse(
        val successful : Boolean = false,
        val customSearchResultDto : CustomSearchResultDto? = null)