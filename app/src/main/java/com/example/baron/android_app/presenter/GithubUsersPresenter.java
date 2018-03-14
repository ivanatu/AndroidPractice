package com.example.baron.android_app.presenter;

import com.example.baron.android_app.model.GithubUsers;
import com.example.baron.android_app.service.GithubService;

import java.util.List;

/**
 * Created by baron on 13/03/2018.
 */

public class GithubUsersPresenter {

    private GithubService githubService;

    private GithubUsersPresenter(){
        if(this.githubService == null){
            this.githubService = new GithubService();
        }
    }

    public void getUsers(){
        githubService
                .getAPI()
                .gettotalcount()
                .enqueue(new Callback<Data>(){
                    @Override
                    public void onResponse(){
                        Data data = response.body();

                        if (data != null && data.getRestResponse() != null) {
                            List<GithubUsers> result = data.getRestResponse().getResult();
                            countryView.countriesReady(result);
                        }
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
