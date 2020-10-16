package com.sega.gistexplorer.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sega.gistexplorer.R;
import com.sega.gistexplorer.model.Gist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import androidx.recyclerview.widget.RecyclerView;

public class GistRecyclerAdapter extends RecyclerView.Adapter<GitsRecyclerViewHolder> {

    private List<Gist> gists;
    private GistViewListener gistViewListener;

    public GistRecyclerAdapter(GistViewListener gistViewListener) {
        this.gistViewListener = gistViewListener;
        gists = new ArrayList<>();
    }

    public interface GistViewListener {
        void onGistItemClick(int position);
    }

    @Override
    public GitsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_gists, parent, false);
        return new GitsRecyclerViewHolder(view, gistViewListener);
    }

    @Override
    public void onBindViewHolder(final GitsRecyclerViewHolder holder, int position) {
        final Gist gist = gists.get(position);
        String firstFile = "N/A";
        if(gist.getFiles() != null && !gist.getFiles().isEmpty()){
            firstFile = ((String)gist.getFiles().keySet().toArray()[0]);
        }

        Glide.with(holder.mView).load(gist.getOwner().getAvatar()).into(holder.avatar);
        holder.login.setText(gist.getOwner().getLogin());
        holder.files.setText(holder.mView.getResources().getString(R.string.file,  firstFile));
        holder.description.setText(gist.getDescription());
        holder.created.setText(holder.mView.getResources().getString(R.string.created,  formatDate(gist.getCreated())));
        holder.comments.setText(holder.mView.getResources().getString(R.string.comments,  String.valueOf(gist.getComments())));
        holder.bind(position, gists);
    }

    @Override
    public int getItemCount() {
        return gists.size();
    }

    public void setGists(List<Gist> gists){
        this.gists = gists;
    }

    public List<Gist> getGists() {
        return gists;
    }

    private String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

}