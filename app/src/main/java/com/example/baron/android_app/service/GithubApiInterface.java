package com.example.baron.android_app.service;

import com.example.baron.android_app.model.GithubUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by baron on 12/03/2018.
 */

public interface GithubApiInterface {

    @GET("users")
    Call<GithubUsersResponse> gettotalcount();

}
