package com.example.credr.snapsearch

import com.example.credr.snapsearch.results.model.SearchResultsResponse
import com.example.credr.snapsearch.search.model.AutoSuggestionsResponse
import com.example.credr.snapsearch.utils.AUTO_SUGGESTION_BASE_URL
import com.example.credr.snapsearch.utils.SEARCH_URL
import com.example.credr.snapsearch.utils.generatePostParamsForAutoSuggestion
import com.google.gson.JsonObject
import rx.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by punitdama on 30/12/17.
 */
@Singleton
class SearchRepository @Inject constructor(val apiService: ApiService){

    fun fetchAutoSuggestions(keyword : String) : Single<AutoSuggestionsResponse>{
        return apiService.post<AutoSuggestionsResponse>(
                AUTO_SUGGESTION_BASE_URL,
                JsonObject().generatePostParamsForAutoSuggestion(keyword))
    }

    fun fetchResultsByKeyword(keyword: String,category_xpath : String?=null) : Single<SearchResultsResponse>{
        return apiService.get<SearchResultsResponse>(
                category_xpath?.let {
                    "$SEARCH_URL&categoryXPath=$category_xpath&keyword=$keyword"
                } ?: "$SEARCH_URL&keyword=$keyword")
    }

}