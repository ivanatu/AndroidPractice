package com.example.baron.android_app.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.baron.android_app.model.GithubUsers;
import com.example.baron.android_app.model.GithubUsersResponse;
import com.example.baron.android_app.service.GithubApiInterface;
import com.example.baron.android_app.service.GithubService;
import com.example.baron.android_app.view.MainActivity;

import adapter.GithubAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.*;

/**
 * Created by baron on 13/03/2018.
 */

public class GithubUsersPresenter {

    private GithubService githubService;
    private Context context;

    public GithubUsersPresenter(Context context) {
        this.context = context;
        if (this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    public interface ViewGithubUsers{
        void displayGithubUsers(ArrayList<GithubUsers> developerlists);
    }

    public void getUsers(final MainActivity main) {
//        final RecyclerView recyclerView = null;
        try{
            githubService
                    .getAPI()
                    .getDevelopers()
                    .enqueue(new Callback<GithubUsersResponse>() {

                        @Override
                        public void onResponse(Call<GithubUsersResponse> call,
                                               Response<GithubUsersResponse> response) {
                            ArrayList<GithubUsers> developers = response.body().getDevelopers();

                            if (developers != null) {
//
//                                recyclerView.setAdapter(adapter);
//                                recyclerView.smoothScrollToPosition(0);
                                main.displayGithubUsers(developers);
                            }
                        }

                        @Override
                        public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                            try {
                                throw new InterruptedException("Something went wrong!");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }catch (Exception e ){
            Log.d("Error", e.getMessage());
        }

    }
}

