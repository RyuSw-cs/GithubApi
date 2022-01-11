package com.ryusw.afreecatvteamproject.Data.Entity;

import java.io.Serializable;

public class UserData implements Serializable {
    private String avatar_url;

    public UserData(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
