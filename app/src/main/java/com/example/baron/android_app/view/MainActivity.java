package com.example.baron.android_app.view;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baron.android_app.R;
import com.example.baron.android_app.model.GithubUsers;
import com.example.baron.android_app.presenter.GithubUsersPresenter;

import java.util.ArrayList;
import adapter.GithubAdapter;
import utils.NetworkUtility;

public class MainActivity extends AppCompatActivity implements GithubUsersPresenter.ViewGithubUsers{

    private GithubUsersPresenter presenter = new GithubUsersPresenter(MainActivity.this);
    public final static String LIST_KEY = "list_state";
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.Adapter mAdapter;
    private ProgressBar loader;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<GithubUsers> developers = new ArrayList<>();
    Parcelable list;
    RecyclerView mRecyclerView;
    TextView Disconnected;
    NetworkUtility networkUtility;
    Boolean isConnected;
    Snackbar snackbar;
    Toolbar appBar;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appBar = findViewById(R.id.app_bar);
        appBar.setTitle("Github Users");
        setSupportActionBar(appBar);
        setSwipeRefreshLayout();
        checkConnection(savedInstanceState);

    }
    public void checkConnection(final Bundle savedInstanceState) {

        networkUtility = new NetworkUtility(this);
        isConnected = networkUtility.isWifiConnected();
        displayGithubUsers(developers);

        if (savedInstanceState != null && savedInstanceState.containsKey(LIST_KEY)) {
            onRestoreInstanceState(savedInstanceState); // Restore data found in the Bundle


        } else {
            if (isConnected) {
                queryApi(swipeRefreshLayout);
            } else {
                displaySnackBar(swipeRefreshLayout, R.string.no_connection, developers);
            }
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

    private void setSwipeRefreshLayout() {
        final Bundle savedInstanceState=null;
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkConnection(savedInstanceState);
            }
        });
    }

    public void dismissDialog(String fetchStatus) {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }

        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            if ("success".equalsIgnoreCase(fetchStatus)) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(this, "Github Users list refreshed",
                        Toast.LENGTH_LONG).show();
            } else {
                swipeRefreshLayout.setRefreshing(false);
            }
        }

        isConnected = networkUtility.isWifiConnected();

        if (!("success".equalsIgnoreCase(fetchStatus))) {
            int networkStatus =  R.string.no_connection;
            if (isConnected) {
                networkStatus = R.string.fetch_failed;
            }

            displaySnackBar(swipeRefreshLayout, networkStatus, developers);
        }
    }

    public void displaySnackBar(final SwipeRefreshLayout swipeRefreshLayout,
                                int internetStatus, final ArrayList<GithubUsers> developers) {

        CharSequence actionText = "RETRY";
        final Bundle savedInstanceState=null;

        if (developers == null) {
            actionText = "TRY AGAIN";
        }

        snackbar = Snackbar
                .make(swipeRefreshLayout, internetStatus, Snackbar.LENGTH_INDEFINITE)
                .setAction(actionText, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            checkConnection(savedInstanceState);
                    }
                });

        // Change text color for action button
        snackbar.setActionTextColor(Color.CYAN);

        // Change snack bar message text color
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout)snackbar.getView();

        layout.setPadding(0, 0, 0, 0);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher_round, 0, 0, 0);
        textView.setCompoundDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.snackbar_icon_padding));
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public void queryApi(SwipeRefreshLayout swipeRefreshLayout) {
        if (!swipeRefreshLayout.isRefreshing()) {
            pd = ProgressDialog.show(this, "Loading Kenya Java Developers",
                    "Loading... Please wait!!!", false, false);
        }
        presenter.getUsers(this);
    }

    @Override
    public void displayGithubUsers(ArrayList<GithubUsers> developerlists) {

        developers=developerlists;
        mRecyclerView = findViewById(R.id.list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter adapter = new GithubAdapter(developerlists, this);
        mRecyclerView.setAdapter(adapter);

        dismissDialog("success");
    }
}
