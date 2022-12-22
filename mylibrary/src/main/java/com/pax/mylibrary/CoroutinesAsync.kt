package com.pax.mylibrary

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

//launch函数只是可以执行一段逻辑，却无法拿到执行的结果，因为它的返回值只是一个job对象
//想拿到返回值，可以使用Async函数

fun main() {

    //async函数必须在协程作用域中才能使用，跟launch函数一样
    //这个函数返回一个Deferred对象，想要拿到返回值，需要调用此对象的await方法

    runBlocking {
        /*val i = async {
            //当调用了async函数后，里面的代码块会立刻开始执行，当调用到await方法时，如果还没执行完，那么await会将当前协程阻塞，直到获取到函数执行结果
            //下面代码，会等待两秒才输出10
            delay(2000)
            5 + 5
        }.await()

        println(i)*/

        /*val start = System.currentTimeMillis()
        val result1 = async {
            delay(1000)
            5 + 5
        }.await()
        println("result1 ---> $result1")
        val result2 = async {
            delay(1000)
            4 + 6
        }.await()
        println("result2 ---> $result2")
        println("result is ${result1 + result2}.")
        val end = System.currentTimeMillis()
        println("cost ${end - start} ms.")*/

        //这种写法明显是非常低效的，因为两个async函数完全可以同时执行从而提高运行效率，所以，可以在仅用到async的结果的时候，再去调用await函数获取结果
        val start = System.currentTimeMillis()
        val result1 = async {
            delay(1000)
            5 + 5
        }

        val result2 = async {
            delay(1000)
            4 + 6
        }

        println("result is ${result1.await() + result2.await()}.")
        val end = System.currentTimeMillis()
        println("cost ${end - start} ms.")

        //此时两个async函数并行运行，大大缩短了时间
        /*-----------------------------------------*/
    }
    println("runBlocking finished")
}