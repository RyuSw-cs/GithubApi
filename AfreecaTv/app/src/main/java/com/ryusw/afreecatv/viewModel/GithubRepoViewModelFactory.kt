package com.ryusw.afreecatv.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ryusw.afreecatv.repository.GithubRepository

class GithubRepoViewModelFactory(private val repository: GithubRepository):ViewModelProvider.Factory {
    /* viewModel 의 인자 전달을 위해 생성 */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GithubRepoViewModel(repository) as T
    }
}