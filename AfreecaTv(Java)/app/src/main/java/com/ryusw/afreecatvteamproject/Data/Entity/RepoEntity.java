package com.ryusw.afreecatvteamproject.Data.Entity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RepoEntity {
    @GET("/search/repositories")
    Call<GitData>getRepoData(
            @Header("accept")String accept,
            @Query("q") String inputKeyword,
            @Query("per_page")int page,
            @Query("page")int section
    );
}
