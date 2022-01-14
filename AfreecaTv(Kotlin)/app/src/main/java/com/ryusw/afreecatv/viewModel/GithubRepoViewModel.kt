package com.ryusw.afreecatv.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ryusw.afreecatv.models.RepoModel
import com.ryusw.afreecatv.paging.GithubRepository
import kotlinx.coroutines.flow.Flow

/**
 * 메인 엑티비티를 표시하기 위한 viewModel
 * 메인 액티비티에서 keyword를 가져온다.
 */
class GithubRepoViewModel constructor(private val repository: GithubRepository) : ViewModel() {

    private var oldKeyword:String? = null
    private var oldResult: Flow<PagingData<RepoModel>>? = null

    fun searchRepo(queryString: String): Flow<PagingData<RepoModel>> {
        val lastResult = oldResult
        if (queryString == oldKeyword && lastResult != null) {
            return lastResult
        }
        oldKeyword = queryString
        val newResult: Flow<PagingData<RepoModel>> = repository.getKeyword(queryString)
            .cachedIn(viewModelScope)
        oldResult = newResult
        return newResult
    }
}
