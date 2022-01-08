package com.ryusw.afreecatv.data.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseService {
    //메소드 이름 getRetrofit, 매개변수를 baseUrl(String), 반환형은 Retrofit 이 될거같음.

    fun getRetrofit(baseUrl: String): Retrofit? = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
