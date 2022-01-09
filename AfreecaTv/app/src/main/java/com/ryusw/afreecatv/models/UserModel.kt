package com.ryusw.afreecatv.models

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("avatar_url")
    val avatarUrl: String
)