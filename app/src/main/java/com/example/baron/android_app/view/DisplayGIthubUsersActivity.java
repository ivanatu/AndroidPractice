package com.example.baron.android_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //INITIALIZE VIEWS
        imageView =  findViewById(R.id.imageView1);
        username = findViewById(R.id.username1);
        github=  findViewById(R.id.github1);

        //RECEIVE DATA
        Intent i=this.getIntent();
        String image2 = i.getExtras().getString("profileImage");
        String username2=i.getExtras().getString("username");
        String github2=i.getExtras().getString("github");

        //BIND DATA
        Glide.with(this)
                .load(image2)
                .placeholder(R.drawable.ic_image_black_24dp)
                .into(imageView);
        username.setText(username2);
        github.setText(github2);
        Linkify.addLinks(github, Linkify.WEB_URLS);

    }

    private Intent createShareForcastIntent(){
        String username2=getIntent().getExtras().getString("username");
        String github2=getIntent().getExtras().getString("github");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome developer  " + username2 + "," + github2)
                .getIntent();
        return  shareIntent;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForcastIntent());
        return true;
    }

}
