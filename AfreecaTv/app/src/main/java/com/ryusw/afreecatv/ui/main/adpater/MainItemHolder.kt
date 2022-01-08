package com.ryusw.afreecatv.ui.main.adpater

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryusw.afreecatv.data.models.Model.GithubRepoModel
import kotlinx.android.synthetic.main.list_main.view.*

class MainItemHolder(view: View, listener: MainAdapter.OnRepoClickListener?):RecyclerView.ViewHolder(view) {
    private val img: ImageView = view.main_repo_img
    private val title: TextView = view.main_repo_title
    private val lang : TextView = view.main_repo_language

    //생성자를 통해 인스턴스가 생성될 때, init 을 사용해줌
    //Constructor 는 보조 생성자임
    init {
        view.setOnClickListener {
            listener?.onItemClick(adapterPosition)
        }
    }
    fun bind(data: GithubRepoModel){
        //파라미터가 없으니 run, 있으면 let
        data.run {
            Glide.with(itemView)
                .load(owner.avatarUrl)
                .into(img)
            title.text = fullName
            lang.text = language
        }
    }
}