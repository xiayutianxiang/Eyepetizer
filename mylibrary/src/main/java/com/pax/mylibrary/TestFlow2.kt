package com.pax.mylibrary

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    /*launch {
        for (i in 1..3) {
            println("not block $i")
            delay(1000)
        }
    }*/

    //收集值
    //t2simple().collect { value -> println(value) }

    //Flow 是一种类似于序列的冷流 — 这段 flow 构建器中的代码直到流被收集的时候才运行
    println("Calling simple function...")
    val flow = t2simple2()
    println("Calling collect...")
    flow.collect { value -> println(value) }
    println("Calling collect again...")
    flow.collect { value -> println(value) }

    println("------------")

    (1..3).asFlow().map { request -> performRequest(request) }
        .collect { response -> println(response) }
}

fun t2simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(1000)
        emit(i) //发射值
    }
}

fun t2simple2(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(1000)
        emit(i)
    }
}

suspend fun performRequest(request: Int): String {
    delay(1000)
    return "response $request"
}