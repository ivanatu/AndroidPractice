package com.example.baron.android_app.model;
import android.os.Parcel;
import android.os.Parcelable;


import com.google.gson.annotations.SerializedName;

/**
 * Created by baron on 12/03/2018.
 */

public class GithubUsers implements Parcelable{
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

    public GithubUsers(Parcel in) {
        username = in.readString();
        profileImage = in.readString();
        github = in.readString();
    }

    public static final Creator<GithubUsers> CREATOR =
            new Creator<GithubUsers>() {
        @Override
        public GithubUsers createFromParcel(Parcel in) {
            return new GithubUsers(in);
        }

        @Override
        public GithubUsers[] newArray(int size) {
            return new GithubUsers[size];
        }
    };

    public String getProfileImage() {
        return profileImage;
    }

    public String getUserName() {
        return username;
    }

    public String getGithub() {
        return github;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(profileImage);
        parcel.writeString(github);
    }
}
