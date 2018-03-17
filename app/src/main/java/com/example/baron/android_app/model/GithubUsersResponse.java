package com.example.baron.android_app.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by baron on 12/03/2018.
 */

public class GithubUsersResponse {

    @SerializedName("items")
    public ArrayList<GithubUsers> developers;

    public ArrayList<GithubUsers> getDevelopers() {
        return developers;
    }
}
