package com.ryusw.afreecatv.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ryusw.afreecatv.data.repository.GithubRepository

class MainViewModelFactory(private val githubRepository: GithubRepository):ViewModelProvider.Factory {
    //만약 viewModel 에서 생성자에 파라미터가 존재하면 Factory 를 해줘야함.
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return modelClass.getConstructor(GithubRepository::class.java).newInstance(githubRepository)
    }
}