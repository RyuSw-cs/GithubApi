package com.ryusw.afreecatvteamproject.UseCase;

import android.widget.Toast;

import com.ryusw.afreecatvteamproject.CallBack.RetrofitOnResult;
import com.ryusw.afreecatvteamproject.Data.Entity.GitData;
import com.ryusw.afreecatvteamproject.Data.Repository.RepoRepository;
import com.ryusw.afreecatvteamproject.UI.Main.MainActivity;

public class RepoUseCase implements RetrofitOnResult {

    private RepoRepository repoRepository;
    private MainActivity mainActivity;

    public RepoUseCase(MainActivity mainActivity){
        this.repoRepository = new RepoRepository(this);
        this.mainActivity = mainActivity;
    }

    public void searchRepo(String keyword,int page, int section){
        repoRepository.retrieveRepo(keyword,page,section);
    }

    @Override
    public void onSuccess(GitData items) {
        //이거를 메인에 가져가기
        mainActivity.getListData(items);
    }

    @Override
    public void onFailure() {
        //오류발생
    }
}
