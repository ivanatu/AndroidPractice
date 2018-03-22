package com.example.baron.android_app.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;

import com.example.baron.android_app.R;
import com.example.baron.android_app.model.GithubUsers;
import com.example.baron.android_app.presenter.GithubUsersPresenter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    TextView Disconnected;

    Toolbar appBar;
    CollapsingToolbarLayout collapse;
    private RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<GithubUsers> developers = new ArrayList<>();
    private GithubUsersPresenter presenter = new GithubUsersPresenter(MainActivity.this);
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
        pd.show();

        mRecyclerView = findViewById(R.id.list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
//        mAdapter = new GithubAdapter(developers, this);
//        mRecyclerView.setAdapter(mAdapter);
        presenter.getUsers(mRecyclerView);

//        swipeRefreshLayout = findViewById(R.id.swipe);
//        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Disconnected = findViewById(R.id.disconnected);
//                try{
//                    presenter.getUsers(mRecyclerView);
//                    swipeRefreshLayout.setRefreshing(false);
//                }catch(Exception e ){
//                    Log.d("Error", e.getMessage());
//                    Disconnected.setVisibility(mRecyclerView.VISIBLE);
//                    Toast.makeText(MainActivity.this, "Error Fetching data", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(MainActivity.this, "Github users Refreshed", Toast.LENGTH_SHORT).show();
//            }
//        });

        pd.dismiss();

    }


//    private void loadJSON() {
//
//    }


}
