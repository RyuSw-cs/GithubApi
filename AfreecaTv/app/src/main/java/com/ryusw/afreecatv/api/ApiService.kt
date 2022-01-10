package com.ryusw.afreecatv.api

import com.ryusw.afreecatv.models.DataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * HTTP Method를 선언하기 위한 인터페이스
 */
interface ApiService {
    @GET("/search/repositories")
    suspend fun getRepoData(
        @Header("accept") accept: String,
        @Query("q") inputKeyword: String,
        @Query("per_page") page: Int,
        @Query("page") section: Int
    ): Response<DataModel>
}