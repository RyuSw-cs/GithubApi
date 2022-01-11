package com.ryusw.afreecatvteamproject.Manager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static String baseUrl = "https://api.github.com";
    private static NetworkManager instance;
    private Retrofit retrofit;

    private NetworkManager(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static NetworkManager getInstance(){
        if(instance == null){
            instance = new NetworkManager();
        }
        return instance;
    }
    public Retrofit getRetrofit(){return retrofit;}
}
