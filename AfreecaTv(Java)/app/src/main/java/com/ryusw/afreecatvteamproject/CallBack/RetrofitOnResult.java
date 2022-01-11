package com.ryusw.afreecatvteamproject.CallBack;

import com.ryusw.afreecatvteamproject.Data.Entity.GitData;
import com.ryusw.afreecatvteamproject.Data.Entity.RepoData;

import java.util.ArrayList;

public interface RetrofitOnResult {
    void onSuccess(GitData items);
    void onFailure();
}
