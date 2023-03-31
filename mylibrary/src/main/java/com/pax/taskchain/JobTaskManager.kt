package com.pax.taskchain

import android.content.Context
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

object JobTaskManager {

    var curLevel = 1

    //存放任务,key表示优先级
    val jobMap = linkedMapOf(
        1 to TaskJobOne(),
        2 to TaskJobTwo(),
        3 to TaskJobThree()
    )

    val stateFlow = MutableStateFlow(curLevel)

    fun doJob(context: Context, job: SingleJob) {
        if (job.handle()) {
            job.launch(context) {
                curLevel++
                if (curLevel <= jobMap.size) {
                    stateFlow.value = curLevel
                }
            }
        } else {
            curLevel++
            if (curLevel <= jobMap.size) {
                stateFlow.value = curLevel
            }
        }
    }
}