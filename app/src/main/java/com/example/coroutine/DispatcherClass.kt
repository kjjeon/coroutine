package com.example.coroutine

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun defaultDispatcher() = runBlocking {
    val task = launch {
        printCurrentThread()
    }
    task.join()
}

fun newThreadDispatcher() = runBlocking {
    val dispatcher = newSingleThreadContext(name = "newThread")
    val task = GlobalScope.launch(dispatcher) {
        printCurrentThread()
    }
    task.join()
}

fun printCurrentThread() {
    Log.d("LOG", "thread : ${Thread.currentThread().name}")
}