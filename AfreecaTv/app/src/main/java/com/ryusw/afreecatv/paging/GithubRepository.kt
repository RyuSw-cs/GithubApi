package com.ryusw.afreecatv.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ryusw.afreecatv.module.AppModule

class GithubRepository {
    fun getKeyword(keyword: String) =
         Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubRepoPagingSource(AppModule.api, keyword) }
        ).liveData
}