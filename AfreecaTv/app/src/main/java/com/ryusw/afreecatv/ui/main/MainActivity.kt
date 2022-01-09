package com.ryusw.afreecatv.ui.main

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryusw.afreecatv.adapter.RepoLoadStateAdapter
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
    }

    private fun setup() {
        binding.mainSearchInputText.setOnEditorActionListener { text, id, keyEvent ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyBoard()
                loadingData(text.text.toString())
            }
            true
        }

        mAdapter = RepoPagedAdapter()
        mAdapter.addLoadStateListener { combinedLoadStates ->
            binding.apply {
                recyclerView.adapter = mAdapter.withLoadStateFooter(
                    footer = RepoLoadStateAdapter{mAdapter.retry()}
                )
            }
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun loadingData(keyword: String) {
        viewModel.word = keyword
        lifecycleScope.launch {
            viewModel.list.collect { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun hideKeyBoard() {
        currentFocus?.run {
            val temp = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            temp?.hideSoftInputFromWindow(windowToken, 0)
        }
    }

    private fun onKeyBoard() {
        currentFocus?.run {
            val temp = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            temp?.showSoftInput(binding.mainSearchInputText, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}