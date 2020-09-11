package com.tvacstudio.tabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Post post;
    private ArrayList<Post> posts = new ArrayList<>();
    Context context;

    public PostAdapter(Context context, ArrayList<Post> posts) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        holder.tittle.setText(posts.get(position).getTitle());
        holder.disc.setText(posts.get(position).getBody());
        holder.id.setText("Id : " + posts.get(position).getId().toString());
//        holder.id.setText("Id : " + String.valueOf(posts.get(position).getId()));
        holder.postid.setText("User Id : " + posts.get(position).getUserId().toString());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tittle;
        private TextView disc;
        private TextView id;
        private TextView postid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.Tittle);
            disc = itemView.findViewById(R.id.Disc);
            id = itemView.findViewById(R.id.Id);
            postid = itemView.findViewById(R.id.PostId);

        }
    }
}
