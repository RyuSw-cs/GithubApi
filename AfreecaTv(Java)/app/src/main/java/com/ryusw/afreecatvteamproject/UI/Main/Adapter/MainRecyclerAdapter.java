package com.ryusw.afreecatvteamproject.UI.Main.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ryusw.afreecatvteamproject.Data.Entity.RepoData;
import com.ryusw.afreecatvteamproject.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.http.GET;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private ArrayList<RepoData>repoDataList;

    public MainRecyclerAdapter(ArrayList<RepoData>getList){
        repoDataList = getList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_main,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(repoDataList.get(position).getFull_name());
        holder.language.setText(repoDataList.get(position).getLanguage());
        Glide.with(holder.itemView)
                .load(repoDataList.get(position).getOwner().getAvatar_url())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return repoDataList.size();
    }

    public void refresh(){
        notifyDataSetChanged();
    }

    //스크롤시 데이터 업데이트.
    public void dataUpdate(ArrayList<RepoData>newList){
        for(int i = 0; i<newList.size(); i++){
            repoDataList.add(newList.get(i));
        }
        //데이터를 다 가져왔다면! -> 여기서 끝내주세요
        refresh();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView title, language;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.main_repo_img);
            title = itemView.findViewById(R.id.main_repo_title);
            language = itemView.findViewById(R.id.main_repo_language);
        }
    }
}
