package com.example.baron.android_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<AndelaDeveloper> developers;
    private Context context;


    public MyAdapter(ArrayList<AndelaDeveloper> developers, Context context) {
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
        AndelaDeveloper andeladeveloper = developers.get(position);

        holder.github.setText(andeladeveloper.getgithub());
        holder.location.setText(andeladeveloper.getlocation());


//
////        holder.mView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (null != mListener) {
////                    // Notify the active callbacks interface (the activity, if the
////                    // fragment is attached to one) that an item has been selected.
////                    mListener.onListFragmentInteraction(holder.mItem);
////                }
////            }
////        });
    }

    @Override
    public int getItemCount() {
        return developers.size();
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
