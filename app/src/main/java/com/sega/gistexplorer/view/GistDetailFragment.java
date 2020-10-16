package com.sega.gistexplorer.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sega.gistexplorer.R;
import com.sega.gistexplorer.model.Gist;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GistDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
        loadComponents(view);
        return view;
    }

    private void loadComponents(View view){
        Glide.with(view).load(selectedGist.getOwner().getAvatar()).into((ImageView) view.findViewById(R.id.avatar));
        ((TextView)view.findViewById(R.id.owner)).setText(selectedGist.getOwner().getLogin());
    }
}