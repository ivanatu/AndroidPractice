package com.example.baron.android_app.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baron.android_app.R;
import com.example.baron.android_app.model.GithubUsers;
import com.example.baron.android_app.presenter.GithubUsersPresenter;

import java.io.Serializable;
import java.util.ArrayList;

import adapter.GithubAdapter;


public class MainActivity extends AppCompatActivity implements Serializable{
    RecyclerView mRecyclerView;
    TextView Disconnected;

    private RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<GithubUsers> developers = new ArrayList<>();
    Parcelable list;
    private GithubUsersPresenter presenter = new GithubUsersPresenter(MainActivity.this);
    public final static String LIST_KEY = "list_state";
    private SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        if (savedInstanceState != null && savedInstanceState.containsKey(LIST_KEY)) {
            onRestoreInstanceState(savedInstanceState); // Restore data found in the Bundle
            mRecyclerView.scrollToPosition(savedInstanceState.getInt("position"));
        }
        else {
            pd.show();
            presenter.getUsers(mRecyclerView);
            pd.dismiss();
        }

    }

    private void initViews() {
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Github Users...");
        pd.setCancelable(false);

        mRecyclerView = findViewById(R.id.list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
//        mAdapter = new GithubAdapter(developers, this);
//        mRecyclerView.setAdapter(mAdapter);
//        presenter.getUsers(mRecyclerView);

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Disconnected = findViewById(R.id.disconnected);
                try{
                    presenter.getUsers(mRecyclerView);
                    swipeRefreshLayout.setRefreshing(false);
                }catch(Exception e ){
                    Log.d("Error", e.getMessage());
                    Disconnected.setVisibility(mRecyclerView.VISIBLE);
                    Toast.makeText(MainActivity.this, "Error Fetching data", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Github users Refreshed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        list = mRecyclerView.getLayoutManager().onSaveInstanceState();
//        outState.putInt("position", mRecyclerView.getLayoutPosition());
        outState.putParcelableArrayList(LIST_KEY, developers);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            developers = savedInstanceState.getParcelableArrayList(LIST_KEY);
            displayRecycleView(developers);

        }
        super.onRestoreInstanceState(savedInstanceState);
    }
    public void displayRecycleView(ArrayList<GithubUsers> users){
        RecyclerView.Adapter adapter = new GithubAdapter(users, this);
        mRecyclerView.setAdapter(adapter);
    }

//    private void loadJSON() {
//
//    }


}
