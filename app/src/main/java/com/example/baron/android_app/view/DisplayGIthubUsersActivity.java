package com.example.baron.android_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baron.android_app.R;

/**
 * Created by baron on 09/03/2018.
 */

public class DisplayGIthubUsersActivity extends AppCompatActivity {
    ImageView imageView;
    TextView username;
    TextView github;
    Toolbar appBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        appBar = findViewById(R.id.app_bar);
//        ImageView headerImage = findViewById(R.id.app_bar_image);
        appBar.setTitle("User Details");
        setSupportActionBar(appBar);


        //INITIALIZE VIEWS
        imageView =  findViewById(R.id.imageView1);
        username = findViewById(R.id.username1);
        github=  findViewById(R.id.github1);

        //RECEIVE DATA
        Intent i=this.getIntent();
        String image2 = i.getExtras().getString("profileImage");
        final String username2=i.getExtras().getString("username");
        final String github2=i.getExtras().getString("github");

        //BIND DATA
        Glide.with(this)
                .load(image2)
                .placeholder(R.drawable.ic_image_black_24dp)
                .into(imageView);
        username.setText(username2);
        github.setText(github2);
        Linkify.addLinks(github, Linkify.WEB_URLS);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Check out this awesome developer @"+username2+", "+github2;
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"Subject here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                startActivity(sharingIntent);

            }
        });
    }

}
