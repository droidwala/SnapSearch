package com.example.credr.snapsearch.search

import android.app.Activity
import android.os.Bundle
import com.example.credr.snapsearch.R
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by punitdama on 30/12/17.
 */
class SearchActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        back_button.setOnClickListener { finish() }
    }
}