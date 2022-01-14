package com.ryusw.afreecatv.module

import com.ryusw.afreecatv.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit을 사용하여 API 통신
 */

object AppModule {
    private const val BASE_URL = "https://api.github.com"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //lazy 를 통해 초기화가 필요한 시점에 초기화 -> 또한 변하지 않기 때문에 val 로 사용한다.
    val api:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}