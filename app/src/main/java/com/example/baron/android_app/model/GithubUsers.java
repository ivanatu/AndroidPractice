package com.example.baron.android_app.model;
import com.google.gson.annotations.SerializedName;

/**
 * Created by baron on 12/03/2018.
 */

public class GithubUsers {
    @SerializedName("login") // from github
    private String username; // your custom name

    @SerializedName("avatarUrl")
    private String profileImage;

    @SerializedName("htmlUrl")
    private String github;

    @SerializedName("organizations_url")
    private String location;

    public GithubUsers(String username, String location){
        this.username=username;
        this.profileImage=profileImage;
        this.github=github;
        this.location=location;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getgithub() {
        return github;
    }

    public void setgithub(String github) {
        this.github = github;
    }

    public String getlocation() {
        return location;
    }

    public void setlocation(String location) {
        this.location = location;
    }


}
