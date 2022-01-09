package com.ryusw.afreecatv.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ryusw.afreecatv.api.ApiService
import com.ryusw.afreecatv.paging.GithubRepoPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GithubRepoViewModel
@Inject constructor(private val apiService: ApiService) : ViewModel() {
    lateinit var word: String
    val list = Pager(PagingConfig(pageSize = 10)) {
        GithubRepoPagingSource(apiService, word)
    }.flow.cachedIn(viewModelScope)
}