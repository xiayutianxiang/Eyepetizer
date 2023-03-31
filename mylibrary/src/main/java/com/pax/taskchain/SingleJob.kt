package com.pax.taskchain

import android.content.Context

interface SingleJob {
    fun handle(): Boolean

    fun launch(context: Context, callback: () -> Unit)
}