package com.ryusw.afreecatv.ui.main

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryusw.afreecatv.adapter.RepoLoadStateAdapter
import com.ryusw.afreecatv.adapter.RepoPagedAdapter
import com.ryusw.afreecatv.databinding.ActivityMainBinding
import com.ryusw.afreecatv.repository.GithubRepository
import com.ryusw.afreecatv.viewModel.GithubRepoViewModel
import com.ryusw.afreecatv.viewModel.GithubRepoViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mAdapter: RepoPagedAdapter by lazy { RepoPagedAdapter() }
    private lateinit var viewModel: GithubRepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val repo = GithubRepository()
        val viewModelFactory = GithubRepoViewModelFactory(repo)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GithubRepoViewModel::class.java)

        binding.searchBtn.setOnClickListener {
            viewControl(false)
            binding.mainSearchInputText.requestFocus()
            onKeyBoard()
        }

        binding.mainSearchInputText.setOnEditorActionListener { text, id, keyEvent ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyBoard()
                search(text.text.toString())
            }
            true
        }

        mAdapter.addLoadStateListener {
            binding.apply {
                recyclerView.adapter = mAdapter.withLoadStateHeaderAndFooter(
                    header = RepoLoadStateAdapter { mAdapter.retry() },
                    footer = RepoLoadStateAdapter { mAdapter.retry() },
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

        viewModel.result.observe(this) {
            mAdapter.submitData(lifecycle, it)
            binding.recyclerView.scrollToPosition(0)
        }
    }

    /* 키워드 값 변경 */
    private fun search(keyword: String) {
        viewModel.getKeyword(keyword)
    }

    private fun hideKeyBoard() {
        currentFocus?.run {
            val temp = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            temp?.hideSoftInputFromWindow(windowToken, 0)
            binding.mainSearchInputText.clearFocus()
            binding.searchText.text = binding.mainSearchInputText.text
            viewControl(true)
        }
    }

    private fun onKeyBoard() {
        currentFocus?.run {
            val temp = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            temp?.showSoftInput(binding.mainSearchInputText, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun viewControl(searching: Boolean) {
        binding.searchText.isVisible = searching
        binding.searchTextTitle.isVisible = searching
        binding.mainSearchInputText.isVisible = !searching
    }
}
