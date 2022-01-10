package com.ryusw.afreecatv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ryusw.afreecatv.databinding.RepoListLayoutBinding
import com.ryusw.afreecatv.models.RepoModel

/**
 * 레포지토리 데이터를 담아두는 리스트를 위한 어댑터
 */
class RepoPagedAdapter : PagingDataAdapter<RepoModel, RepoPagedAdapter.ViewHolder>(diffCallback) {

    inner class ViewHolder(val binding: RepoListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(repo: RepoModel){
                binding.apply {
                    mainRepoTitle.text = repo?.fullName
                    mainRepoLanguage.text = repo?.language
                    mainRepoImg.load(repo?.owner?.avatarUrl) {
                        crossfade(true)
                        crossfade(1000)
                    }
                }
            }
        }
    /* DiffUtil 사용 */
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RepoModel>() {
            override fun areItemsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null){
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RepoListLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
}