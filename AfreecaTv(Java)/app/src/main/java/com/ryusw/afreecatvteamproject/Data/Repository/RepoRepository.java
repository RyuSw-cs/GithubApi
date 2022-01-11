package com.ryusw.afreecatvteamproject.Data.Repository;

import android.util.Log;
import android.widget.Toast;

import com.ryusw.afreecatvteamproject.Data.Entity.GitData;
import com.ryusw.afreecatvteamproject.Data.Entity.RepoEntity;
import com.ryusw.afreecatvteamproject.Manager.NetworkManager;
import com.ryusw.afreecatvteamproject.UseCase.RepoUseCase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoRepository {

    private RepoEntity repoEntity;
    private RepoUseCase repoUseCase;
    public RepoRepository(RepoUseCase repoUseCase){
        repoEntity = NetworkManager.getInstance().getRetrofit().create(RepoEntity.class);
        this.repoUseCase = repoUseCase;
    }
    public void retrieveRepo(String keyword, int page, int section){
        Call<GitData> getRepoData = repoEntity.getRepoData("application/vnd.github.v3+json",keyword,page,section);
        getRepoData.enqueue(new Callback<GitData>() {
            @Override
            public void onResponse(Call<GitData> call, Response<GitData> response) {
                if(response.isSuccessful()){
                    //데이터 받아오기 성공
                    Log.d("레포 데이터","성공");
                    repoUseCase.onSuccess(response.body());
                }
                else{
                    //만약 마지막 데이터 라면?
                    repoUseCase.onFailure();
                    Log.d("레포 데이터","실패");
                }
            }

            @Override
            public void onFailure(Call<GitData> call, Throwable t) {
                Log.d("레포 데이터","실패");
                repoUseCase.onFailure();
            }
        });
    }
}
