package com.ryusw.afreecatv.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ryusw.afreecatv.module.AppModule

class GithubRepository {
    fun getKeyword(keyword: String) =
         Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 1000,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { GithubRepoPagingSource(AppModule.api, keyword) }
        ).flow
}