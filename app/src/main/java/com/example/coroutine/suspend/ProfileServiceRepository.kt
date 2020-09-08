package com.example.coroutine.suspend

import kotlinx.coroutines.Deferred

interface ProfileServiceRepository {
    fun asyncFetchByName(name: String): Deferred<String>
    suspend fun fetchByName(nmae: String): String
}