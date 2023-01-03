package com.pax.mylibrary

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {

    /*runBlocking {
        flow {
            println("send hello")
            emit("hello")
            println("send world")
            emit("world")
        }.flowOn(Dispatchers.IO)
            .onEmpty {
                println("onEmpty")
            }
            .onStart {
                println("onStart") //上游flow{}开始发送数据之前执行
            }
            .onEach {
                println("onEach $it")
            }
            .onCompletion {
                println("onCompletion") //flow数据流结束或者取时执行
            }
            .catch { e -> e.message?.let { println(it) } }
            .collect {
                println("collect: $it")
            }
    }*/

    simple().forEach { println(it) }
    println("--------")
    simple2().forEach { println(it) }
    println("--------")
    runBlocking {
        simple3().forEach { println(it) }
    }
}

fun simple(): List<Int> = listOf(1, 2, 3)

fun simple2(): Sequence<Int> = sequence {
    for (i in 1..3) {
        Thread.sleep(1000)
        yield(i)    //产生下一个值
    }
}

suspend fun simple3(): List<Int> {
    delay(1000)
    return listOf(1, 2, 3)
}