package com.sega.gistexplorer.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sega.gistexplorer.R;
import com.sega.gistexplorer.data.GistPresenter;
import com.sega.gistexplorer.model.Gist;
import com.sega.gistexplorer.view.adapter.GistRecyclerAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class GistFragment extends Fragment implements GistView, GistRecyclerAdapter.GistViewListener {

    private GistPresenter presenter;
    private RecyclerView recyclerView;
    private GistRecyclerAdapter gistRecyclerAdapter;

    public GistFragment() {}

    public static GistFragment newInstance() {
        GistFragment fragment = new GistFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new GistPresenter(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity().isFinishing()) {
            presenter.clearDisposable();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.startScheduledJob();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gists_list, container, false);

        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        gistRecyclerAdapter = new GistRecyclerAdapter(this);
        recyclerView.setAdapter(gistRecyclerAdapter);
        presenter.fetchGists();

        return view;
    }

    @Override
    public void refreshGistTable(List<Gist> gists) {
        Log.d(this.getClass().getSimpleName(), "Refreshing table.");
        if (gistRecyclerAdapter != null) {
            gistRecyclerAdapter.setGists(gists);
            gistRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(String message) {
        Toast.makeText(getActivity(), message,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final SwipeRefreshLayout pullToRefresh = getView().findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            presenter.fetchGists();
            pullToRefresh.setRefreshing(false);
        });

    }

    @Override
    public void onGistItemClick(int position) {
        if (gistRecyclerAdapter != null && gistRecyclerAdapter.getItemCount() != 0 &&
                position < gistRecyclerAdapter.getItemCount()) {
            Gist selectedGist = gistRecyclerAdapter.getGists().get(position);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, GistDetailFragment.newInstance(selectedGist)).commit();
        }
    }

}