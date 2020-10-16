package com.sega.gistexplorer.view.adapter;

import android.view.View;
import android.widget.TextView;

import com.sega.gistexplorer.R;

import androidx.recyclerview.widget.RecyclerView;

public class FilesListViewHolder extends RecyclerView.ViewHolder {

    public final View mView;
    public final TextView fileName;
    public final TextView type;
    public final TextView language;
    public final TextView url;
    public final TextView size;

    public FilesListViewHolder(View view) {
        super(view);

        mView = view;
        fileName = view.findViewById(R.id.fileName);
        type = view.findViewById(R.id.type);
        language = view.findViewById(R.id.language);
        url = view.findViewById(R.id.url);
        size = view.findViewById(R.id.size);

    }

}