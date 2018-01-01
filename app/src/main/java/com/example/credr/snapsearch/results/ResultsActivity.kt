package com.example.credr.snapsearch.results

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.credr.snapsearch.R
import com.example.credr.snapsearch.utils.plusAssign
import com.example.credr.snapsearch.utils.schedulers.BaseSchedulerProvider
import com.example.credr.snapsearch.utils.toast
import com.example.credr.snapsearch.utils.viewutils.GridDividerDecoration
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.android.synthetic.main.toolbar_with_title.*
import rx.subscriptions.CompositeSubscription
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by punitdama on 31/12/17.
 */
const val CATEGORY_XPATH = "category_xpath"
const val KEYWORD = "keyword"

//Entry point
inline fun <reified T: Activity> Context.start(keyword : String,category_xpath : String? = null){
    return startActivity(
            Intent(this,T::class.java)
                    .apply {
                        putExtra(KEYWORD,keyword)
                        putExtra(CATEGORY_XPATH,category_xpath)
                    }
    )
}

class ResultsActivity : DaggerAppCompatActivity(){

    //Input variables
    lateinit var keyword : String
    var category_xpath : String? = null

    @Inject lateinit var viewModel : ResultsViewModel
    @Inject lateinit var scheduler : BaseSchedulerProvider

    val compositeSubscription by lazy(LazyThreadSafetyMode.NONE) { CompositeSubscription() }
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { ResultsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        keyword = intent.getStringExtra(KEYWORD)
        category_xpath = intent.getStringExtra(CATEGORY_XPATH)

        toolbarSetUp()
        rv.recyclerViewSetUp()

        compositeSubscription += viewModel.fetchResults(keyword,category_xpath)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({Timber.d("Results fetched successfully")},
                        {error -> Timber.d("Error fetching results ${error.localizedMessage}")})

        compositeSubscription += viewModel.viewState()
                .observeOn(scheduler.ui())
                .subscribe(this::render,
                        {error -> Timber.d("Error rendering view state {${error.localizedMessage}")})

    }

    private fun render(viewState: ResultsViewState){
        when(viewState.isLoading){
            true -> loader.visibility = View.VISIBLE
            else -> loader.visibility = View.GONE
        }

        viewState.error?.let { toast(it) }

        viewState.products?.let {
            if(it.isEmpty()){
                no_results_found.visibility = View.VISIBLE
                no_results_found.text = getString(R.string.no_results_found,keyword)
            }
            else {
                no_results_found.visibility = View.GONE
                adapter.addProducts(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeSubscription.clear()
    }

    private fun toolbarSetUp(){
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }
        toolbar.setNavigationOnClickListener { finish() }
        page_title.text = keyword
    }

    private val recyclerViewSetUp : RecyclerView.() -> Unit = {
        layoutManager = GridLayoutManager(this@ResultsActivity,2)
        addItemDecoration(GridDividerDecoration(this@ResultsActivity))
        adapter = this@ResultsActivity.adapter
    }
}