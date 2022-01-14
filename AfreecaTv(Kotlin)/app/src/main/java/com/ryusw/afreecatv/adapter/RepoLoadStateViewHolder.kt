package com.ryusw.afreecatv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.ryusw.afreecatv.R
import com.ryusw.afreecatv.databinding.RepoListFooterBinding

class RepoLoadStateViewHolder(
    private val binding: RepoListFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.apply {
            loadingData.isVisible = loadState is LoadState.Loading
            errorText.isVisible = loadState is LoadState.Error
        }
    }
    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): RepoLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.repo_list_footer, parent, false)
            val binding = RepoListFooterBinding.bind(view)
            return RepoLoadStateViewHolder(binding, retry)
        }
    }
}