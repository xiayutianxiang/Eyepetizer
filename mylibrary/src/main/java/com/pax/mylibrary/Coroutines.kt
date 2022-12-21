package com.pax.mylibrary

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    /**
     * GlobalScope.launch 函数，创建一个协程的作用域,里面的代码就是在协程中运行了
     * 只是在代码块中打印了一行日志。那么
    现在运行main()函数，日志能成功打印出来吗？如果你尝试一下，会发现没有任何日志输出。
    这是因为，Global.launch函数每次创建的都是一个顶层协程，这种协程当应用程序运行结束
    时也会跟着一起结束。刚才的日志之所以无法打印出来，就是因为代码块中的代码还没来得及
    运行，应用程序就结束了。
     */
    GlobalScope.launch {
        println("codes run in coroutine scope")
        delay(1500) //只会挂起当前协程，不会影响其他的
        println("codes run in coroutine scope finished")
    }
    //此处令程序等待一秒，再结束，保证代码可以执行
    //仍有问题，如果代码块1秒内执行不完，还是会中断
    Thread.sleep(1000)

    //让程序在代码执行完了之后再结束，可以使用runBlocking
    runBlocking {
        println("codes run in coroutine scope")
        delay(1500) //只会挂起当前协程，不会影响其他的
        println("codes run in coroutine scope finished")
    }
}