package com.ryusw.afreecatv.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ryusw.afreecatv.repository.GithubRepository

class GithubRepoViewModel constructor(private val repository: GithubRepository) : ViewModel() {
    private val keywordString: MutableLiveData<String> = MutableLiveData()

    val result = keywordString.switchMap { keywordString ->
        repository.getKeyword(keywordString).cachedIn(viewModelScope)
    }

    fun getKeyword(keyword: String) {
        keywordString.value = keyword
    }
}
