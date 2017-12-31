package com.example.credr.snapsearch.utils.schedulers

import rx.Scheduler

/**
 * Created by punitdama on 31/12/17.
 */
interface BaseSchedulerProvider{

    fun computation() : Scheduler

    fun io() : Scheduler

    fun ui() : Scheduler
}