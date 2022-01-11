package com.ryusw.afreecatvteamproject.Data.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GitData implements Serializable {
    private int total_count;
    private ArrayList<RepoData> items;

    public GitData(ArrayList<RepoData> items) {
        this.items = items;
    }

    public ArrayList<RepoData> getItems() {
        return items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
