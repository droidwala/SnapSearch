package com.example.credr.snapsearch.di

import com.example.credr.snapsearch.results.ResultsActivity
import com.example.credr.snapsearch.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module class for telling Dagger which all SubComponents needs to be created for Activities
 * By adding @ContributesAndroidInjector() dagger.android generates all subcomponents for us
 * Created by punitdama on 31/12/17.
 */
@Module
abstract class ActivityBindingModule{

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun searchActivity() : SearchActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun resultsActivity() : ResultsActivity
}