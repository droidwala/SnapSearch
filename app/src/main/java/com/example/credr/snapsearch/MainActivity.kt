package com.example.credr.snapsearch

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.credr.snapsearch.search.SearchActivity
import kotlinx.android.synthetic.main.toolbar_with_search.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_box.setOnClickListener {
            startActivity(Intent(this,SearchActivity::class.java))
        }
    }
}
