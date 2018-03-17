package com.example.baron.android_app.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baron.android_app.R;
import com.example.baron.android_app.model.GithubUsers;

import java.util.ArrayList;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.ViewHolder> {
    private ArrayList<GithubUsers> developers;
    private Context context;


    public GithubAdapter(ArrayList<GithubUsers> developers, Context context) {
        this.developers = developers;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GithubUsers githubusers = developers.get(position);

        holder.github.setText(githubusers.getgithub());
        holder.location.setText(githubusers.getlocation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent detailIntent = new Intent(v.getContext(), DisplayMessageActivity.class);
              detailIntent.putExtra("github", githubusers.getgithub());
              detailIntent.putExtra("location", githubusers.getlocation());
//              detailIntent.putExtra("GITURL", gitUrl);
              v.getContext().startActivity(detailIntent);
          }
       });
    }

    @Override
    public int getItemCount() {
        return developers.size();
    }

    private void openDetailActivity(String github,String location)

    {
        Intent i=new Intent(context, DisplayMessageActivity.class);

        //PACK DATA TO SEND
        i.putExtra("github",github);
        i.putExtra("location", location);
//        i.putExtra("IMAGE_KEY",image);

        //open activity
        context.startActivity(i);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  ImageView imageView;
        public  TextView github;
        public  TextView location;

//        String githubtext;
//        String locationtext;
//        Drawable image;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            github = (TextView) view.findViewById(R.id.github);
            location = (TextView) view.findViewById(R.id.location);
        }


//    @Override
//    public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}
