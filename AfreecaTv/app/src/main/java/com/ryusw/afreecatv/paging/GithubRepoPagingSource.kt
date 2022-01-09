package com.ryusw.afreecatv.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ryusw.afreecatv.api.ApiService
import com.ryusw.afreecatv.models.RepoModel

class GithubRepoPagingSource(private val apiService: ApiService,private val word: String) :
    PagingSource<Int, RepoModel>() {
    override fun getRefreshKey(state: PagingState<Int, RepoModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoModel> {
        //EditText 에서 가져온 값을 받아와야함
        return try {
            val currentPage = params.key ?: 1
            //내가 검색을 누르면? 검색값을 가지면서 탐색
            val response = apiService.getRepoData("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", word, 10, currentPage)
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