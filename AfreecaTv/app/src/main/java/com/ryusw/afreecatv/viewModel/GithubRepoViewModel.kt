package com.ryusw.afreecatv.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ryusw.afreecatv.paging.GithubRepository

/**
 * 메인 엑티비티를 표시하기 위한 viewModel
 * 메인 액티비티에서 keyword를 가져온다.
 */
class GithubRepoViewModel constructor(private val repository: GithubRepository) : ViewModel() {
    private val currentKeyword : MutableLiveData<String> = MutableLiveData()

    val list = currentKeyword.switchMap { queryString ->
        repository.getKeyword(queryString).cachedIn(viewModelScope)
    }

    fun getKeyword(keyword: String){
        currentKeyword.value = keyword
    }
}
