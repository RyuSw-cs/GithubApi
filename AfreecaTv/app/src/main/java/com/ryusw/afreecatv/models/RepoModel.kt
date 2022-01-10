package com.ryusw.afreecatv.models

import com.google.gson.annotations.SerializedName

data class RepoModel(
    val id: Int,
    @SerializedName("full_name")
    val fullName: String,
    val owner: UserModel,
    val language: String
)