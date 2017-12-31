package com.example.credr.snapsearch.di

import android.app.Application
import android.content.Context
import com.example.credr.snapsearch.utils.schedulers.BaseSchedulerProvider
import com.example.credr.snapsearch.utils.schedulers.SchedulerProvider
import dagger.Binds
import dagger.Module

/**
 * We use this to bind our App class as a Context in the AppComponent
 * Since we are using Dagger Android we do not need to pass our Application instance to any module
 * as was done in previous versions of dagger,
 * we simply need to expose our Application as Context and Dagger Android will inject it for us
 * Created by punitdama on 31/12/17.
 */
@Module
abstract class AppModule{
    @Binds
    abstract fun bindContext(app : Application) : Context

    @Binds
    abstract fun provideSchedulerProvider(provider : SchedulerProvider) : BaseSchedulerProvider
}