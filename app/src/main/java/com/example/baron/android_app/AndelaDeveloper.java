package com.example.baron.android_app;

/**
 * Created by baron on 07/03/2018.
 */

public class AndelaDeveloper{

    private String github, location;

    public AndelaDeveloper() {
    }

    public AndelaDeveloper( String github, String location) {
        this.github = github;
        this.location = location;
    }


    public String getlocation() {
        return location;
    }

    public void setlocation(String location) {
        this.location = location;
    }


    public String getgithub() {
        return github;
    }

    public void setgithub(String github) {
        this.github = github;
    }

}