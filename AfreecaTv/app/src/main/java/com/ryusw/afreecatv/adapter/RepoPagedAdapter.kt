package com.ryusw.afreecatv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ryusw.afreecatv.databinding.RepoListLayoutBinding
import com.ryusw.afreecatv.models.RepoModel

class RepoPagedAdapter : PagingDataAdapter<RepoModel, RepoPagedAdapter.ViewHolder>(diffCallback) {

    inner class ViewHolder(val binding: RepoListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<RepoModel>() {
            override fun areItemsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean {
                return oldItem.fullName == newItem.fullName
            }

            override fun areContentsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            mainRepoTitle.text = currentItem?.fullName
            mainRepoLanguage.text = currentItem?.language
            mainRepoImg.load(currentItem?.owner?.avatarUrl) {
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RepoListLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}