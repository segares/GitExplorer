package com.sega.gistexplorer.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sega.gistexplorer.R;
import com.sega.gistexplorer.model.Gist;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class GitsRecyclerViewHolder extends RecyclerView.ViewHolder {

    private GistRecyclerAdapter.GistViewListener gistViewListener;
    public final View mView;
    public final ImageView avatar;
    public final TextView login;
    public final TextView files;
    public final TextView description;
    public final TextView created;
    public final TextView comments;
    public Gist item;

    public GitsRecyclerViewHolder(View view, GistRecyclerAdapter.GistViewListener gistViewListener) {
        super(view);
        this.gistViewListener = gistViewListener;
        mView = view;
        avatar = view.findViewById(R.id.avatar);
        login = view.findViewById(R.id.login);
        files = view.findViewById(R.id.files);
        description = view.findViewById(R.id.description);
        created = view.findViewById(R.id.created);
        comments = view.findViewById(R.id.comments);
    }

    public void bind(int position, List<Gist> gistList) {
        if (position >= gistList.size()) return;
        itemView.setOnClickListener(view -> itemClick(position));
    }


    public void itemClick(int position){
        gistViewListener.onGistItemClick(position);
    }

}