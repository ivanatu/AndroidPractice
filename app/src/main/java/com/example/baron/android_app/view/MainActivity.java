package com.example.baron.android_app.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;

import com.example.baron.android_app.R;
import com.example.baron.android_app.model.GithubUsers;
import com.example.baron.android_app.presenter.GithubUsersPresenter;
import java.util.ArrayList;

import adapter.GithubAdapter;


public class MainActivity extends AppCompatActivity implements GithubUsersPresenter.ViewGithubUsers{
    RecyclerView mRecyclerView;
    TextView Disconnected;

    Toolbar appBar;
    private RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<GithubUsers> developers = new ArrayList<>();

    Parcelable list;
    private GithubUsersPresenter presenter = new GithubUsersPresenter(MainActivity.this);
    public final static String LIST_KEY = "list_state";
    private SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBar = findViewById(R.id.app_bar);
//        ImageView headerImage = findViewById(R.id.app_bar_image);
        setSupportActionBar(appBar);

        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Github Users...");
        pd.setCancelable(false);

        displayGithubUsers(developers);

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Disconnected = findViewById(R.id.disconnected);
                try{
                    presenter.getUsers(MainActivity.this);
                    Toast.makeText(MainActivity.this, "Github users Refreshed",
                            Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }catch(Exception e ){
                    Log.d("Error", e.getMessage());
                    Disconnected.setVisibility(mRecyclerView.VISIBLE);
                    Toast.makeText(MainActivity.this, "Error Fetching data",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(LIST_KEY)) {
           onRestoreInstanceState(savedInstanceState); // Restore data found in the Bundle
        }
        else {
            pd.show();
            presenter.getUsers(this);
            pd.dismiss();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(LIST_KEY, developers);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            developers = savedInstanceState.getParcelableArrayList(LIST_KEY);
            displayRecycleView(developers);
        }
    }
    public void displayRecycleView(ArrayList<GithubUsers> users){
        RecyclerView.Adapter adapter = new GithubAdapter(users, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void displayGithubUsers(ArrayList<GithubUsers> users) {
        developers=users;
        mRecyclerView = findViewById(R.id.list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter adapter = new GithubAdapter(users, this);
        mRecyclerView.setAdapter(adapter);

    }

}
