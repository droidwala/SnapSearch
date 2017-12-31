package com.example.credr.snapsearch.search

import com.example.credr.snapsearch.search.model.AutoSuggestion
import com.xwray.groupie.GroupAdapter

/**
 * Created by punitdama on 31/12/17.
 */
class AutoSuggestionsAdapter : GroupAdapter(){

    fun addSuggestions(suggestions : List<AutoSuggestion>){
        clear()
        for(suggestion in suggestions){
            add(SuggestedItem(suggestion))
        }
    }
}