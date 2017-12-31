package com.example.credr.snapsearch.search

import com.example.credr.snapsearch.ApiService
import com.example.credr.snapsearch.search.model.AutoSuggestion
import com.example.credr.snapsearch.search.model.AutoSuggestionsResponse
import com.example.credr.snapsearch.utils.AUTO_SUGGESTION_BASE_URL
import com.example.credr.snapsearch.utils.generatePostParamsForAutoSuggestion
import com.google.gson.JsonObject
import rx.Single
import javax.inject.Inject

/**
 * Created by punitdama on 30/12/17.
 */
class SearchRepository @Inject constructor(val apiService: ApiService){

    fun fetchAutoSuggestions(keyword : String) : Single<AutoSuggestionsResponse>{
        return apiService.post<AutoSuggestionsResponse>(
                AUTO_SUGGESTION_BASE_URL,
                JsonObject().generatePostParamsForAutoSuggestion(keyword))
    }

}