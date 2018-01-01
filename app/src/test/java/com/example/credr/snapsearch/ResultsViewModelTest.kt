package com.example.credr.snapsearch

import com.example.credr.snapsearch.results.ResultsViewModel
import com.example.credr.snapsearch.results.ResultsViewState
import com.example.credr.snapsearch.results.model.CustomSearchResultDto
import com.example.credr.snapsearch.results.model.Product
import com.example.credr.snapsearch.results.model.SearchResultDTOMobile
import com.example.credr.snapsearch.results.model.SearchResultsResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import rx.Single
import rx.observers.TestSubscriber

const val CATEGORY_PATH = "ALL"
/**
 * Created by punitdama on 01/01/18.
 */
class ResultsViewModelTest{
    @Mock
    private lateinit var repository : SearchRepository

    private lateinit var viewModel : ResultsViewModel

    private lateinit var testObserver : TestSubscriber<ResultsViewState>
    private lateinit var resultsObserver : TestSubscriber<SearchResultsResponse>

    private lateinit var products : List<Product>

    private lateinit var resultsViewStateLoading : ResultsViewState
    private lateinit var resultsViewStateError : ResultsViewState
    private lateinit var resultsViewStateWithData : ResultsViewState
    private lateinit var resultsViewStateWithNoData : ResultsViewState


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        testObserver = TestSubscriber()
        resultsObserver = TestSubscriber()

        products = listOf(
                Product("Uncharted 4",2900,1500, listOf("uc4.png"),5.0f),
                Product("Uncharted 3",1800,900, listOf("uc3.png"),4.7f),
                Product("Uncharted 2",1400,700, listOf("uc2.png"),4.1f),
                Product("Uncharted 1",1200,600, listOf("uc1.png"),4.0f)
        )

        resultsViewStateLoading = ResultsViewState(true,null,null)
        resultsViewStateError = ResultsViewState(false,"Error message",null)
        resultsViewStateWithData = ResultsViewState(false,null,products)
        resultsViewStateWithNoData = ResultsViewState(false,null, emptyList<Product>())

    }

    @Test
    fun populateProducts_WithAvailableKeyword(){
        val keyword = "Uncharted"
        `when`(repository.fetchResultsByKeyword(keyword, CATEGORY_PATH))
                .thenReturn(Single.just(SearchResultsResponse(true, CustomSearchResultDto(SearchResultDTOMobile(products)))))

        viewModel = ResultsViewModel(repository)
        viewModel.viewState().subscribe(testObserver)
        viewModel.fetchResults(keyword, CATEGORY_PATH).subscribe(resultsObserver)

        verify(repository).fetchResultsByKeyword(keyword, CATEGORY_PATH)

        testObserver.assertValues(resultsViewStateLoading,resultsViewStateWithData)

    }

    @Test
    fun populateProducts_WithUnAvailableKeyword(){
        val keyword = "zzzz"

        `when`(repository.fetchResultsByKeyword(keyword, CATEGORY_PATH))
                .thenReturn(Single.just(SearchResultsResponse(true, CustomSearchResultDto(SearchResultDTOMobile(null)))))

        viewModel = ResultsViewModel(repository)
        viewModel.viewState().subscribe(testObserver)
        viewModel.fetchResults(keyword, CATEGORY_PATH).subscribe(resultsObserver)

        verify(repository).fetchResultsByKeyword(keyword, CATEGORY_PATH)

        testObserver.assertValues(resultsViewStateLoading,resultsViewStateWithNoData)
    }

    @Test
    fun errorFetchingProducts_ShowError(){
        val keyword = "random"
        `when`(repository.fetchResultsByKeyword(keyword, CATEGORY_PATH))
                .thenReturn(Single.error(Exception("Error message")))

        viewModel = ResultsViewModel(repository)
        viewModel.viewState().subscribe(testObserver)
        viewModel.fetchResults(keyword, CATEGORY_PATH).subscribe(resultsObserver)

        verify(repository).fetchResultsByKeyword(keyword, CATEGORY_PATH)

        testObserver.assertValues(resultsViewStateLoading,resultsViewStateError)

    }

}