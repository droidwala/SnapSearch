package com.example.credr.snapsearch.search

import android.os.Bundle
import com.example.credr.snapsearch.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by punitdama on 30/12/17.
 */
class SearchActivity : DaggerAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        back_button.setOnClickListener { finish() }
    }
}