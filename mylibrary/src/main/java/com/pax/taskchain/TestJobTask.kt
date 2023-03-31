package com.pax.taskchain

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

fun main() {
    MainScope().launch {
        JobTaskManager.apply {
            stateFlow.collect {
                flow {
                    emit(jobMap[it])
                }.collect {
                //    doJob(this, it)
                }
            }
        }
    }
}