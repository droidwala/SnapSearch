package com.example.credr.snapsearch.results.model

/**
 * Created by punitdama on 31/12/17.
 */
data class SearchResultsResponse(
        var successful : Boolean = false,
        var customSearchResultDto : CustomSearchResultDto? = null)