package com.example.credr.snapsearch.search

import com.example.credr.snapsearch.R
import com.example.credr.snapsearch.databinding.ItemSuggestionBinding
import com.example.credr.snapsearch.search.model.AutoSuggestion
import com.xwray.groupie.Item

/**
 * ViewHolder showing individual suggestion item in Suggestions List
 * Created by punitdama on 31/12/17.
 */
class SuggestedItem(private val suggestion : AutoSuggestion,private val clickManager: ClickManager) : Item<ItemSuggestionBinding>(){

    override fun getLayout() = R.layout.item_suggestion

    override fun bind(viewBinding: ItemSuggestionBinding, position: Int) {
        viewBinding.suggestedItem.text = suggestion.keyword

        viewBinding.root.setOnClickListener {
            clickManager.openResultsScreen(suggestion)
        }
    }

}