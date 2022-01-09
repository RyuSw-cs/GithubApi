package com.ryusw.afreecatv.di

<<<<<<< HEAD
=======
import com.ryusw.afreecatv.Util.GithubService
>>>>>>> 72da1d3513d37de918d511e6a1cb337e6d1536d8
import com.ryusw.afreecatv.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val baseUrl = "https://api.github.com"

    @Provides
    fun provideBaseUrl() = baseUrl

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl:String):ApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}