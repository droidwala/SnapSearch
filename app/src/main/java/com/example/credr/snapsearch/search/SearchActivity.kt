package com.example.credr.snapsearch.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.credr.snapsearch.R
import com.example.credr.snapsearch.search.model.SearchActivityViewState
import com.example.credr.snapsearch.utils.plusAssign
import com.example.credr.snapsearch.utils.schedulers.BaseSchedulerProvider
import com.example.credr.snapsearch.utils.toast
import com.jakewharton.rxbinding.widget.RxTextView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import rx.Observable
import rx.subscriptions.CompositeSubscription
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by punitdama on 30/12/17.
 */
class SearchActivity : DaggerAppCompatActivity(){

    @Inject lateinit var scheduler : BaseSchedulerProvider
    @Inject lateinit var viewModel : SearchViewModel
    private val compositeSubscription by lazy(LazyThreadSafetyMode.NONE) { CompositeSubscription() }
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { AutoSuggestionsAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        back_button.setOnClickListener { finish() }
        rv.adapter = adapter

        viewModel.processIntent(searchIntent())

        compositeSubscription += viewModel.viewState()
                .observeOn(scheduler.ui())
                .subscribe((this::render), {error -> Timber.d("Error rendering view state ${error.localizedMessage}" ) })
    }

    private fun searchIntent() : Observable<CharSequence>{
        return RxTextView.textChanges(search).skip(1).debounce(500,TimeUnit.MILLISECONDS,scheduler.ui())
                .filter { it -> !it.isNullOrEmpty() && it.length > 2 };
    }

    private fun render(viewState : SearchActivityViewState){
        Timber.d("render called ")
        when {
            viewState.isLoading -> search_loader.visibility = View.VISIBLE
            else -> search_loader.visibility = View.GONE
        }

        viewState.error?.let { toast(it) }

        viewState.autosuggestions?.let { adapter.addSuggestions(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeSubscription.clear()
    }
}