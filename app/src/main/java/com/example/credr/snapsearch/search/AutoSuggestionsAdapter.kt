package com.example.credr.snapsearch.search

import com.example.credr.snapsearch.search.model.AutoSuggestion
import com.xwray.groupie.GroupAdapter

/**
 * Recyclerview Adapter showing AutoSuggestions when user starts typing query in search box
 * Created by punitdama on 31/12/17.
 */
class AutoSuggestionsAdapter(val clickManager: ClickManager) : GroupAdapter(){

    fun addSuggestions(suggestions : List<AutoSuggestion>){
        clear()//Removes existing results shown
        for(suggestion in suggestions){
            add(SuggestedItem(suggestion,clickManager))
        }
    }
}