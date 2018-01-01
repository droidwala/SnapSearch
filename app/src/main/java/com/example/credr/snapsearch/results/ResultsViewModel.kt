package com.example.credr.snapsearch.results

import com.example.credr.snapsearch.SearchRepository
import com.example.credr.snapsearch.results.model.SearchResultsResponse
import rx.Observable
import rx.Single
import rx.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by punitdama on 31/12/17.
 */
class ResultsViewModel @Inject constructor(val repository: SearchRepository) {

    private val viewState : PublishSubject<ResultsViewState> = PublishSubject.create();

    fun fetchResults(keyword : String, category_xpath : String?): Single<SearchResultsResponse> {
        return repository.fetchResultsByKeyword(keyword,category_xpath)
                .doOnSubscribe(this::loadingStarted)
                .doOnError(this::loadingError)
                .doOnSuccess(this::loadingFinished)
    }


    fun viewState() : Observable<ResultsViewState> {
        return viewState
    }

    private fun loadingStarted(){
        viewState.onNext(ResultsViewState(true, null, null))
    }

    private fun loadingError(e : Throwable){
        viewState.onNext(ResultsViewState(false, e.message, null))
    }

    private fun loadingFinished(searchResultsResponse: SearchResultsResponse?){
        viewState.onNext(ResultsViewState(false, null, searchResultsResponse?.customSearchResultDto?.searchResultDTOMobile?.catalogSearchDTOMobile ?: emptyList()))
    }

}
