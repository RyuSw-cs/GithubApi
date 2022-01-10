package com.ryusw.afreecatv.api

import com.ryusw.afreecatv.models.DataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    /* Http Method 선언 */
    @GET("/search/repositories")
    suspend fun getRepoData(
        @Header("accept") accept: String,
        @Query("q") inputKeyword: String,
        @Query("per_page") page: Int,
        @Query("page") section: Int
    ): Response<DataModel>
}