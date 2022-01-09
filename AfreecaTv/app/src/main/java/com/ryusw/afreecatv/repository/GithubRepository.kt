package com.ryusw.afreecatv.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ryusw.afreecatv.di.AppModule
import com.ryusw.afreecatv.paging.GithubRepoPagingSource

class GithubRepository{
    fun getKeyword(keyword: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {GithubRepoPagingSource(AppModule.api,keyword)}
        ).liveData
}