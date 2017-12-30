package com.example.credr.snapsearch.utils

import android.content.Context
import android.widget.Toast
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Android related extension functions
 * Not much here.
 * But lot more could be added
 */

fun Context.toast(message : String, length : Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,message,length).show()
}

operator fun CompositeSubscription.plusAssign(subscription: Subscription){
    add(subscription)
}

