package com.ryusw.afreecatv.models

import com.google.gson.annotations.SerializedName

data class DataModel (
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<RepoModel>
)
