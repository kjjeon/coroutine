package com.example.coroutine.suspend

import kotlinx.coroutines.runBlocking

fun test1Suspend() = runBlocking {
    val profileServiceRepository = ProfileServiceRepositoryImpl()
    val ret =profileServiceRepository.asyncFetchByName("joel").await()
    println("not recommend solution ret = $ret")
}


fun test2Suspend() = runBlocking {
    val profileServiceRepository = ProfileServiceRepositoryImpl()
    val ret = profileServiceRepository.fetchByName("joel")
    println("recommend solution ret = $ret")
}
