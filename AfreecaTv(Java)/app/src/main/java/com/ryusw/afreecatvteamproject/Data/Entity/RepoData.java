package com.ryusw.afreecatvteamproject.Data.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//items
public class RepoData implements Serializable {
    private String full_name;
    private UserData owner;
    private String language;

    public RepoData(String full_name, UserData owner, String language) {
        this.full_name = full_name;
        this.owner = owner;
        this.language = language;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public UserData getOwner() {
        return owner;
    }

    public void setOwner(UserData owner) {
        this.owner = owner;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
