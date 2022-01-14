package com.ryusw.afreecatv.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 * LoadState에 따라 변화하는 UI를 위한 어댑터
 */
class RepoLoadStateAdapter(
    private val retry: () -> Unit
) :
    LoadStateAdapter<RepoLoadStateViewHolder>() {
    override fun onBindViewHolder(
        holder: RepoLoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): RepoLoadStateViewHolder {
        return RepoLoadStateViewHolder.create(parent,retry)
    }
}