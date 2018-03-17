package adapter;

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
import com.example.baron.android_app.view.DisplayGIthubUsersActivity;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(final GithubAdapter.ViewHolder holder, int position) {
        final GithubUsers githubusers = developers.get(position);

        holder.username.setText(githubusers.getUserName());

        Picasso.with(context)
                .load(githubusers.getProfileImage())
                .placeholder(R.drawable.ic_image_black_24dp)
                .into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent detailIntent = new Intent(v.getContext(), DisplayGIthubUsersActivity.class);
              detailIntent.putExtra("profileImage", githubusers.getProfileImage());
              detailIntent.putExtra("username", githubusers.getUserName());
              detailIntent.putExtra("github", githubusers.getGithub());
              detailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

              v.getContext().startActivity(detailIntent);
          }
       });
    }

    @Override
    public int getItemCount() {
        return developers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  ImageView imageView;
        public  TextView github;
        public  TextView username;


        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            username = view.findViewById(R.id.username);
        }

    }
}
