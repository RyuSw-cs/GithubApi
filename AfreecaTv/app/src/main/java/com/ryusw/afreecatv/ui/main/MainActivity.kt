package com.ryusw.afreecatv.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryusw.afreecatv.R
import com.ryusw.afreecatv.data.models.Model.GithubRepoModel
import com.ryusw.afreecatv.data.repository.GithubRepository
import com.ryusw.afreecatv.ui.main.adpater.MainAdapter
import com.ryusw.afreecatv.viewmodel.MainViewModel
import com.ryusw.afreecatv.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var mainAdapter: MainAdapter
    private var count = 1
    private lateinit var str:String
    private var searching = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initViewModel()
    }

    private fun initView() {
        search_btn.setOnClickListener { onKeyBoard() }
        main_search_input_text.setOnEditorActionListener { text, id, keyEvent ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyBoard()
                main_search_input_text.clearFocus()
                searching = false
                count = 1;
                onSearch()
                search_text.text = main_search_input_text.text.toString()
                main_search_input_text.visibility = View.INVISIBLE
                search_text_title.visibility = View.VISIBLE
                search_text.visibility = View.VISIBLE
            }
            true
        }


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter?.itemCount
                if (lastVisibleItemPosition + 1 == itemTotalCount) {
                    //프로그레스 보여주면서 데이터를 받기 -> 다됐으면 다시 사라지고
                    main_paging_progress_bar.visibility = View.VISIBLE
                    searching = true
                    count++
                    onSearch()
                }
            }
        })
    }

    private fun initViewModel() {
        viewModelFactory = MainViewModelFactory(GithubRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.dataList.observe(this) {
            updateList(it)
        }
    }

    private fun updateList(list: List<GithubRepoModel>) {
        //리플렉션?
        if (::mainAdapter.isInitialized) {
            if(searching) mainAdapter.update(list,searching)
            else mainAdapter.update(list,searching)
        } else {
            //apply
            mainAdapter = MainAdapter(list).apply {
                listener = object : MainAdapter.OnRepoClickListener {
                    override fun onItemClick(position: Int) {
                        //run,let의 차이점
                        mainAdapter.getItem(position).run {
                            //클릭이벤트 구현
                            //openGithub("123")
                        }
                    }
                }
            }
            recyclerView.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = mainAdapter
            }
        }
    }

    private fun openGithub(url: String) {
        try {
            var uri = Uri.parse(url)
            Intent(Intent.ACTION_VIEW, uri).run {
                startActivity(this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /* 버튼 클릭 함 */
    private fun onSearch() {
        main_search_input_text.run {
            viewModel.retrieveRepoData(main_search_input_text.text.toString(), 10, count)
            hideKeyBoard()
        }
    }

    //키보드 내려가기
    private fun hideKeyBoard() {
        currentFocus?.run {
            val temp = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            temp?.hideSoftInputFromWindow(windowToken, 0)
        }
    }

    //키보드 올라가기
    private fun onKeyBoard() {
        if(main_search_input_text.visibility == View.VISIBLE){
            main_search_input_text.requestFocus()
            currentFocus?.run {
                val temp = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                temp?.showSoftInput(main_search_input_text, InputMethodManager.SHOW_IMPLICIT)
            }
        }
        else{
            main_search_input_text.visibility = View.VISIBLE
            main_search_input_text.requestFocus()
            search_text.visibility = View.INVISIBLE
            search_text_title.visibility = View.INVISIBLE
            currentFocus?.run {
                val temp = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                temp?.showSoftInput(main_search_input_text, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }
}