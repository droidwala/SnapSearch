package com.example.credr.snapsearch.search

import com.example.credr.snapsearch.SearchRepository
import com.example.credr.snapsearch.search.model.AutoSuggestionsResponse
import rx.Observable
import rx.schedulers.Schedulers
import rx.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by punitdama on 30/12/17.
 */
class SearchViewModel @Inject constructor(val repository: SearchRepository){

    private val viewState : PublishSubject<SearchViewState> = PublishSubject.create();
    private val searchSubject : PublishSubject<CharSequence> = PublishSubject.create();

    fun processIntent(searchIntent : Observable<CharSequence>){
        searchSubject
                .switchMap(this::search)
                .subscribe ({ Timber.d("SearchSubject results received") },
                        { error -> Timber.d("SearchSubject subscribe error ${error.localizedMessage}")})


        searchIntent.subscribe(searchSubject)

    }

    fun search(keyword : CharSequence) : Observable<AutoSuggestionsResponse> {
        Timber.d("Query is:  $keyword")
        return repository.fetchAutoSuggestions(keyword.toString())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(this::loadingStarted)
                .doOnError(this::loadingError)
                .doOnSuccess(this::loadingFinished)
                .toObservable()
                .onErrorResumeNext(Observable.just(null))
    }


    fun viewState() : Observable<SearchViewState>{
        return viewState
    }

    private fun loadingStarted(){
        viewState.onNext(SearchViewState(true, null, null))
    }

    private fun loadingError(e : Throwable){
        viewState.onNext(SearchViewState(false, e.message, null))
    }

    private fun loadingFinished(autoSuggestionsResponse: AutoSuggestionsResponse){
        viewState.onNext(SearchViewState(false, null, autoSuggestionsResponse.responseAutosuggestions))
    }
}