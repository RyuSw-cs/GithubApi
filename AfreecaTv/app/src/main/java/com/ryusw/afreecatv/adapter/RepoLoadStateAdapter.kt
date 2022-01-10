package com.ryusw.afreecatv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ryusw.afreecatv.databinding.RepoListFooterBinding

/**
 * LoadState에 따라 변화하는 UI를 위한 어댑터
 */
class RepoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<RepoLoadStateAdapter.RepoLoadStateViewHolder>() {
    override fun onBindViewHolder(
        holder: RepoLoadStateAdapter.RepoLoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): RepoLoadStateAdapter.RepoLoadStateViewHolder {
        val binding =
            RepoListFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoLoadStateViewHolder(binding)
    }

    inner class RepoLoadStateViewHolder(private val binding: RepoListFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.apply {
                /* 현재 loadState 에 따른 View 컨트롤 */
                loadingData.isVisible = loadState is LoadState.Loading
                errorText.isVisible = loadState is LoadState.Error
            }
        }
    }
}