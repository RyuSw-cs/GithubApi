package com.ryusw.afreecatv.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryusw.afreecatv.adapter.RepoPagedAdapter
import com.ryusw.afreecatv.databinding.ActivityMainBinding
import com.ryusw.afreecatv.viewmodel.GithubRepoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: RepoPagedAdapter
    private val viewModel: GithubRepoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
        loadingData()
    }
    private fun setup(){
        mAdapter = RepoPagedAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun loadingData(){
        lifecycleScope.launch {
            viewModel.list.collect { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }
}