package com.pax.taskchain

import android.app.AlertDialog
import android.content.Context

class TaskJobThree : SingleJob {
    override fun handle(): Boolean {
        println("start handle job three")
        return true
    }

    override fun launch(context: Context, callback: () -> Unit) {
        println("start launch job three")
        AlertDialog.Builder(context).setMessage("这是第三个弹窗").setPositiveButton("Ok") { _, _ ->
            callback()
        }.show()
    }
}