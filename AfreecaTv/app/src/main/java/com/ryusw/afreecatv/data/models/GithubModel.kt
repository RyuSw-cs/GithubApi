package com.ryusw.afreecatv.data.models.Model

import com.google.gson.annotations.SerializedName

/* data 클래스로 관리함 */

data class GithubDataModel (
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<GithubRepoModel>
)

data class GithubRepoModel(
    @SerializedName("full_name")
    val fullName: String,
    val owner: GithubUserModel,
    val language: String
)

data class GithubUserModel(
    @SerializedName("avatar_url")
    val avatarUrl: String
)


