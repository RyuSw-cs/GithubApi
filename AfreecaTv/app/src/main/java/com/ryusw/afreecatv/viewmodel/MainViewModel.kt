package com.ryusw.afreecatv.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryusw.afreecatv.data.models.Model.GithubRepoModel
import com.ryusw.afreecatv.data.repository.GithubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//ViewModel 을 상속받음
class MainViewModel(private val githubRepository: GithubRepository) : ViewModel() {

    // observer 패턴
    private val getList = MutableLiveData <List<GithubRepoModel>>()
    val dataList = getList

    fun retrieveRepoData(keyword: String, page: Int, section: Int) {
        //코루틴 사용(RxJava 를 대체)
        //IO: 백그라운드 스레드, Main: 메인 스레드
        CoroutineScope(Dispatchers.IO).launch {
            //하고싶은 비동기 작업
            //let, also, with, apply, with
            //let = 파라미터로 명시적 전달 -> getData를 수행한 결과 ->  response
            githubRepository.getData(keyword, page, section)?.let { response ->
                if(response.isSuccessful){
                    //body 는 응답한 값을 반환해준다.
                    response.body()?.let {
                        //postValue = get, setValue = set
                        getList.postValue(it.items)
                    }
                }
                else{
                    Log.d("123","실패")
                }
            }
        }
    }
}