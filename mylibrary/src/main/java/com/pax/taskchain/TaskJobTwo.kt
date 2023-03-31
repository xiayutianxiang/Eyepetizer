package com.pax.taskchain

import android.app.AlertDialog
import android.content.Context

class TaskJobTwo : SingleJob {
    override fun handle(): Boolean {
        println("start handle job two")
        return true
    }

    override fun launch(context: Context, callback: () -> Unit) {
        println("start launch job two")
        AlertDialog.Builder(context).setMessage("这是第二个弹窗").setPositiveButton("Ok") { _, _ ->
            callback()
        }.show()
    }
}