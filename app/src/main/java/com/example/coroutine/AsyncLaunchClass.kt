package com.example.coroutine

import android.util.Log
import kotlinx.coroutines.*
import java.lang.UnsupportedOperationException

//async는 throw 전파가 되지 않고 completed가 뜨고 종료
fun asyncAndJoinError() = runBlocking {
    val task = GlobalScope.async(Dispatchers.Default) {
        throw UnsupportedOperationException()
    }
    task.join()
    Log.d("LOG","Completed")
}

//exception이 throw 되었기 때문에 cancelled 되어 조건에 들어와서 처리 할 수 있음 하지만 throw는 전파되지 않는다.
@OptIn(InternalCoroutinesApi::class)
fun asyncAndJoin() = runBlocking {
    val task = GlobalScope.async(Dispatchers.Default) {
        Log.d("LOG","asyncAndJoin task")
        throw UnsupportedOperationException()
    }
    task.join()
    if(task.isCancelled) {
        val exception = task.getCancellationException()
        Log.d("LOG","exception = $exception")
    }else {
        Log.d("LOG","success")
    }

    Log.d("LOG","Completed")
}

// await를 통해서 throw 까지 전파 할 수 있음
fun asyncAndAwit() = runBlocking {
    val task = GlobalScope.async(Dispatchers.Default) {
        Log.d("LOG","asyncAndAwit task")
        throw UnsupportedOperationException()
        0
    }
    val ret = task.await()
    Log.d("LOG","Completed")
}

//launch는 예외가 전파됨 .
fun launchThrow() = runBlocking {
    val task = GlobalScope.launch(Dispatchers.Default) {
        Log.d("LOG","launchThrow task")
        throw UnsupportedOperationException()
    }
    task.join()
    Log.d("LOG","Completed")
}
