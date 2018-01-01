package com.example.credr.snapsearch

import com.example.credr.snapsearch.search.SearchViewModel
import com.example.credr.snapsearch.search.SearchViewState
import com.example.credr.snapsearch.search.model.AutoSuggestion
import com.example.credr.snapsearch.search.model.AutoSuggestionsResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.Single
import rx.observers.TestSubscriber

/**
 * Created by punitdama on 01/01/18.
 */
class SearchViewModelTest{

    @Mock
    private lateinit var repository : SearchRepository
    private lateinit var searchViewModel : SearchViewModel

    private lateinit var testObserver : TestSubscriber<SearchViewState>

    private lateinit var autoSuggestions : List<AutoSuggestion>
    private lateinit var searchViewStateLoading : SearchViewState
    private lateinit var searchViewStateError : SearchViewState
    private lateinit var searchViewStateLoadedWithData : SearchViewState
    private lateinit var searchViewStateLoadedWithNoData : SearchViewState


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        testObserver = TestSubscriber()
        autoSuggestions = listOf(
                AutoSuggestion("mobile","ALL"),
                AutoSuggestion("mobile accessories","Mobile & Tablets"),
                AutoSuggestion("mobile holders & mounts","ALL")
        )


        searchViewStateLoading = SearchViewState(true,null,null)
        searchViewStateError = SearchViewState(false,"Error message",null)
        searchViewStateLoadedWithData = SearchViewState(false,null,autoSuggestions)
        searchViewStateLoadedWithNoData = SearchViewState(false,null, emptyList<AutoSuggestion>())

    }


    @Test
    fun getAutoSuggestions_WithAvailableKeyword(){
        val keyword = "Mobiles"
        `when`(repository.fetchAutoSuggestions(keyword))
                .thenReturn(Single.just(AutoSuggestionsResponse(true,autoSuggestions)))

        searchViewModel = SearchViewModel(repository)

        searchViewModel.viewState().subscribe(testObserver)
        searchViewModel.processIntent(Observable.just(keyword))

        verify(repository).fetchAutoSuggestions(keyword)

        testObserver.assertValues(searchViewStateLoading,searchViewStateLoadedWithData)

    }

    @Test
    fun getAutoSuggestions_WithUnavaiableKeyword(){
        val keyword = "incorrect"
        `when`(repository.fetchAutoSuggestions(keyword))
                .thenReturn(Single.just(AutoSuggestionsResponse(true, emptyList())))

        searchViewModel = SearchViewModel(repository)

        searchViewModel.viewState().subscribe(testObserver)
        searchViewModel.processIntent(Observable.just(keyword))

        verify(repository).fetchAutoSuggestions(keyword)

        testObserver.assertValues(searchViewStateLoading,searchViewStateLoadedWithNoData)
    }

    @Test
    fun errorFetchingAutoSuggestions_ShowError(){
        val keyword = "Mobiles"
        `when`(repository.fetchAutoSuggestions(keyword))
                .thenReturn(Single.error(Exception("Error message")))

        searchViewModel = SearchViewModel(repository)

        searchViewModel.viewState().subscribe(testObserver)
        searchViewModel.processIntent(Observable.just(keyword))

        verify(repository).fetchAutoSuggestions(keyword)

        testObserver.assertValues(searchViewStateLoading,searchViewStateError)
    }




}