package com.pax.mylibrary

import kotlinx.coroutines.*

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
    //保证协程作用域内的所有代码和子协程没有全部执行完之前一直阻塞当前线程
    runBlocking {
        println("codes run in coroutine scope")
        delay(1500) //只会挂起当前协程，不会影响其他的
        println("codes run in coroutine scope finished")
    }

    println("----------")

    //创建多个协程，使用launch函数 (必须在协程的作用域中才能调用)

    /**
     * 子协程中的日志是交替打印的，说明它是类似多线程那样，并发运行，但是这些协程是运行在同一个线程中的
     * 只是由编程语言决定多个协程之间如何调度，让哪个运行，哪个挂起，调度过程不需要操作系统参与
     */
    runBlocking {
        /*launch {
            println("launch1")
            delay(1000)
            println("launch1 finished")
        }

        launch {
            println("launch2")
            delay(1000)
            println("launch2 finished")
        }

        launch {
            println("launch3")
            delay(1000)
            println("launch3 finished")
        }*/
        launch {

            /**
             * 当一个协程中代码较多，需要抽成一个方法时，此时类似挂起函数delay，无法在普通的函数中调用
             */

            println(printDot())
        }
    }

    //coroutineScope函数和runBlocking函数还有点类似，它可以保证其作用域内的所有代码和子协程在全部执行完之前，外部的协程会一直被挂起
    //下面这个例子，只有coroutineScope中函数全部执行完了，才会输出后面的finish

    /**
     * coroutineScope函数和runBlocking函数的作用是有点类似的，但是
    coroutineScope函数只会阻塞当前协程，既不影响其他协程，也不影响任何线程，因此是不
    会造成任何性能上的问题的。而runBlocking函数由于会挂起外部线程，如果你恰好又在主线
    程中当中调用它的话，那么就有可能会导致界面卡死的情况
     */
    runBlocking {
        coroutineScope {
            launch {
                for (i in 1 until 10) {
                    println(i)
                    delay(1000)
                }
            }
        }
        println("coroutineScope finished")
    }

    //取消协程，可以使用cancel函数。GlobalScope.launch、launch函数都有返回值，返回一个Job对象，取消时调用这个job对象的cancel函数
    val job = GlobalScope.launch {
        println("...")
    }
    job.cancel()
}

/**
 * 使用suspend关键字将函数申明成挂起函数,此时delay这种挂起函数可以正常使用
 * 但是此时无法使用launch函数，因为launch函数必须在协程作用域内才能使用，
 * suspend关键字只是声明了一个挂起函数，无法提供协程作用域
 *
 * 这个问题可以借助coroutineScope函数来解决。coroutineScope函数也是一个挂起函数，
 * 因此可以在任何其他挂起函数中调用。它的特点是会继承外部的协程的作用域并创建一个子协
 * 程，借助这个特性，我们就可以给任意挂起函数提供协程作用域了
 */

suspend fun printDot1() {
    delay(1000)
    println("....")
}

suspend fun printDot() = coroutineScope {
    launch {
        delay(1000)
        println("....")
    }
}

