package com.ryusw.afreecatv.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ryusw.afreecatv.repository.GithubRepository

class GithubRepoViewModelFactory(private val repository: GithubRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GithubRepoViewModel(repository) as T
    }
}