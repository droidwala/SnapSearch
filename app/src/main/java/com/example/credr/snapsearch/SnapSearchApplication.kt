package com.example.credr.snapsearch

import com.example.credr.snapsearch.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * We create a custom Application class that extends  DaggerApplication
 * We then override applicationInjector() which tells Dagger how to make our @Singleton Component
 * We never have to call `component.inject(this)` as DaggerApplication will do that for us.
 *
 * App Navigation:
 * MainActivity() -> SearchActivity() -> ResultsActivity()
 */
class SnapSearchApplication : DaggerApplication(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}