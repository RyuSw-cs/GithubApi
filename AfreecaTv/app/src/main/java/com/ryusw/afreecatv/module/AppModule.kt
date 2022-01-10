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
    val api:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}