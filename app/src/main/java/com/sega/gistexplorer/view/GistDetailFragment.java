package com.sega.gistexplorer.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sega.gistexplorer.R;
import com.sega.gistexplorer.model.File;
import com.sega.gistexplorer.model.Gist;
import com.sega.gistexplorer.view.adapter.FileListAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GistDetailFragment extends Fragment {

    private static final String GIST_PARAM = "GIST";

    private Gist selectedGist;

    public GistDetailFragment() {}

    public static GistDetailFragment newInstance(Gist selectedGist) {
        GistDetailFragment fragment = new GistDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(GIST_PARAM, selectedGist);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedGist = getArguments().getParcelable(GIST_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gist_detail, container, false);

        initComponents(view);

        RecyclerView recyclerView = view.findViewById(R.id.fileList);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        List<File> files = new ArrayList(selectedGist.getFiles().values());
        recyclerView.setAdapter(new FileListAdapter(files));

        return view;
    }

    private void initComponents(View view){
        Glide.with(view).load(selectedGist.getOwner().getAvatar()).into((ImageView) view.findViewById(R.id.avatar));
        ((TextView)view.findViewById(R.id.owner)).setText(selectedGist.getOwner().getLogin());

        view.findViewById(R.id.backButton).setOnClickListener(
                button -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, GistFragment.newInstance()).commit()
        );
    }
}