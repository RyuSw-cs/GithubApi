package com.ryusw.afreecatv.ui.main.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryusw.afreecatv.R
import com.ryusw.afreecatv.data.models.Model.GithubRepoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainAdapter(private var data: List<GithubRepoModel>): RecyclerView.Adapter<MainItemHolder>() {
    //아이템에 대한 리스너를 외부에서 받기 위해 등록
    interface OnRepoClickListener{
        fun onItemClick(position: Int)
    }
    var listener: OnRepoClickListener? = null;

    //아이템뷰의 레이아웃 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_main,parent,false)
        return MainItemHolder(view, listener)
    }

    //앞서 구현했던 bind를 통해 데이터 연결
    override fun onBindViewHolder(holder: MainItemHolder, position: Int) {
        holder.bind(data[position])
    }

    //리스트의 사이즈 만큼
    override fun getItemCount(): Int {
        return data.size
    }

    //데이터가 업데이트
    fun update(updateData: List<GithubRepoModel>, update: Boolean){
        //코루틴으로 메인스레드에서 실행 -> UI 작업을 위해
        CoroutineScope(Dispatchers.Main).launch {
            if(update) data += updateData
            else data = updateData
        }
        notifyDataSetChanged()
    }

    //아이템 정보 가져오기
    fun getItem(position: Int) = data[position]
}