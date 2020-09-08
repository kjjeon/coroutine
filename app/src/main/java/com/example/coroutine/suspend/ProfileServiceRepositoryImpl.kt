package com.example.coroutine.suspend

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ProfileServiceRepositoryImpl : ProfileServiceRepository
{
    //이렇게 직적 deferred를 반환 할 수 있으나 비추천 사용하는 곳에서 await를 써야하고  가시성이 떨어짐
    override fun asyncFetchByName(name: String): Deferred<String> = GlobalScope.async { "JOEL" }
    // suspend를 이용하여 일반 함수 처럼 사용
    override suspend fun fetchByName(nmae: String): String = "JOEL"

}