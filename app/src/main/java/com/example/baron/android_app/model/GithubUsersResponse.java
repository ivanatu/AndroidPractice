package com.example.baron.android_app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by baron on 12/03/2018.
 */

public class GithubUsersResponse {

    @SerializedName("total_count")
    private int totalcount;

    public int getTotalCount() {
        return totalcount;
    }

    public void setTotalCount(int totalcount) {
        this.totalcount = totalcount;
    }
}
