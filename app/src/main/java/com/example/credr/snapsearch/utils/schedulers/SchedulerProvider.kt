package com.example.credr.snapsearch.utils.schedulers

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by punitdama on 31/12/17.
 */
@Singleton
class SchedulerProvider @Inject constructor() : BaseSchedulerProvider{

    override fun computation() = Schedulers.computation()

    override fun io() = Schedulers.io()

    override fun ui() = AndroidSchedulers.mainThread()
}