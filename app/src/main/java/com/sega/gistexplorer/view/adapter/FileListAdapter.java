package com.sega.gistexplorer.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sega.gistexplorer.R;
import com.sega.gistexplorer.model.File;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class FileListAdapter extends RecyclerView.Adapter<FilesListViewHolder> {

    private List<File> files;


    public FileListAdapter(List<File> files) {
        this.files = files;
    }

    @Override
    public FilesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_files, parent, false);
        return new FilesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FilesListViewHolder holder, int position) {
        final File file = files.get(position);

        holder.fileName.setText(holder.mView.getResources().getString(R.string.file,  file.getFilename()));
        holder.type.setText(holder.mView.getResources().getString(R.string.type,  file.getType()));
        holder.language.setText(holder.mView.getResources().getString(R.string.language,  file.getLanguage()));
        holder.url.setText(holder.mView.getResources().getString(R.string.url,  file.getRaw_url()));
        holder.size.setText(holder.mView.getResources().getString(R.string.size,  String.valueOf(file.getSize())));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

}