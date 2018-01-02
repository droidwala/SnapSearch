package com.example.credr.snapsearch.search

import com.example.credr.snapsearch.search.model.AutoSuggestion

/**
 * Contract implemented by SearchActivity to navigate to ResultsActivity
 * when user clicks on one of the suggestion in AutoSuggestions list in SearchActivity
 * Created by punitdama on 31/12/17.
 */
interface ClickManager{
    fun openResultsScreen(suggestion: AutoSuggestion)
}