package com.ryusw.afreecatv.api

import com.ryusw.afreecatv.data.models.Model.GithubDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    //비동기 실행을 위한 suspend(coroutine사용)
    //동일 스레드에서
    @GET("/search/repositories")
    suspend fun getRepoData(
        @Header("accept")accept: String,
        @Query("q")inputKeyword: String,
        @Query("per_page")page: Int,
        @Query("page")section: Int
    ): Response<GithubDataModel>
}