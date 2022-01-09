package com.ryusw.afreecatv.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ryusw.afreecatv.api.ApiService
import com.ryusw.afreecatv.models.RepoModel

class GithubRepoPagingSource(private val apiService: ApiService) :
    PagingSource<Int, RepoModel>() {
    override fun getRefreshKey(state: PagingState<Int, RepoModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoModel> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getRepoData("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", "key", 10, currentPage)
            val data = response.body()?.items ?: emptyList()
            val retrieveData = mutableListOf<RepoModel>()
            retrieveData.addAll(data)
            LoadResult.Page(
                data = retrieveData,
                prevKey = if (currentPage == 1) null
                else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}