package com.bellminp.common

import timber.log.Timber

fun timberMsg(msg : Any,from : String? = null){
    Timber.d("Timber Message $from $msg")
}