package com.example.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutine.suspend.test1Suspend
import com.example.coroutine.suspend.test2Suspend
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import java.lang.UnsupportedOperationException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        launchThrow()
        asyncAndJoin()
        try {
            asyncAndAwit()
        }catch (e:Exception) {
            Log.d("LOG","e: $e")
        }
        defaultDispatcher()
        newThreadDispatcher()
        launchLazyStart()
        jobExceptionHandle()
        jobCancelHandle()
        test1Suspend()
        test2Suspend()
        addContextSample1()
        minusContextSample1()
        withContextSample1()
    }

}