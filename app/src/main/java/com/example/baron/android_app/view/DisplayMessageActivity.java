package com.example.baron.android_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.baron.android_app.R;

//import static android.content.Intent.getIntent;

/**
 * Created by baron on 09/03/2018.
 */

public class DisplayMessageActivity  extends AppCompatActivity {
    TextView github;
    TextView location;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        //INITIALIZE VIEWS
        github= findViewById(R.id.github);
//        img= findViewById(R.id.spacecraftImageDetail);
        location= findViewById(R.id.location);


        //RECEIVE DATA
        Intent i=this.getIntent();
        String github2=i.getExtras().getString("github");
        String location2=i.getExtras().getString("location");
//        int image=i.getExtras().getInt("IMAGE_KEY");

        //BIND DATA
        github.setText(github2);
        location.setText(location2);


    }

}
