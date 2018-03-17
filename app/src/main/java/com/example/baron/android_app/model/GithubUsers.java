package com.example.baron.android_app.model;
import com.google.gson.annotations.SerializedName;

/**
 * Created by baron on 12/03/2018.
 */

public class GithubUsers {
    @SerializedName("login") // from github
    private String username; // your custom name

    @SerializedName("avatar_url")
    private String profileImage;

    @SerializedName("html_url")
    private String github;


    public GithubUsers(String profileImage, String username, String github){
        this.profileImage=profileImage;
        this.username=username;
        this.github=github;
    }
    public String getProfileImage() {
        return profileImage;
    }

    public String getUserName() {
        return username;
    }

    public String getGithub() {
        return github;
    }



}
