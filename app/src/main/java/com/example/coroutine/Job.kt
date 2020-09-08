package com.example.coroutine

import kotlinx.coroutines.*
import java.lang.UnsupportedOperationException


fun launchLazyStart() = runBlocking {
    val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
        println("launchNotStarted")
    }
//    job.start() // 바로 시작되고 기다리지 않는다.
//    job.join() // 바로 시작 되고 작업을 기다린다.
}

fun jobExceptionHandle() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler {
        _ , throwable -> println("Job cancelled due to ${throwable.message}")
    }

    GlobalScope.launch(exceptionHandler) {
        println("start job")
        throw UnsupportedOperationException("test message")
    }
}


fun jobCancelHandle() = runBlocking {
    val job = GlobalScope.launch {
        println("start job")
        delay(5000)
    }.apply {
        invokeOnCompletion {cause ->
            cause?.let {
                println("Job cancelled due to ${it.message}")
            }
        }
    }
    delay(300)
    job.cancel()
}


fun addContextSample1() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler {
            _ , throwable -> println("Job cancelled due to ${throwable.message}")
    }

    GlobalScope.launch(exceptionHandler + newSingleThreadContext("context")) {
        println("addContextSample1 thread : ${Thread.currentThread().name}")
        throw UnsupportedOperationException("addContextSample1")
    }
}


fun minusContextSample1() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler {
            _ , throwable -> println("Job cancelled due to ${throwable.message}")
    }

    val dispatcher = newSingleThreadContext("context")
    var context = exceptionHandler + dispatcher
    context = context.minusKey(dispatcher.key) //여기서 Dispatchers.Main 다시 빠진다
    GlobalScope.launch(context) {
        println("minusContextSample1 thread : ${Thread.currentThread().name}")
//        throw UnsupportedOperationException("minusContextSample1")
    }
}

fun withContextSample1() = runBlocking {
    val dispatcher = Dispatchers.IO // 이거 메인으로 하면 동작 안함 왜???
    val name = withContext(dispatcher) {
        "JOEL"
    }
    println("withContextSample1 name = $name")
}

val iterator = iterator {
    println("yielding 1")
    yield(1)
    println("yielding 2")
    yield(2)
}

