package com.sega.gistexplorer.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sega.gistexplorer.R;
import com.sega.gistexplorer.data.GistDetailPresenter;
import com.sega.gistexplorer.data.GithubFileClient;
import com.sega.gistexplorer.model.File;
import com.sega.gistexplorer.model.Gist;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GistDetailFragment extends Fragment implements GistDetailView {

    private static final String GIST_PARAM = "GIST";

    private Gist selectedGist;
    private GistDetailPresenter presenter;
    private int fileIndexSelected = 0;

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
        presenter = new GistDetailPresenter(this);
        if (getArguments() != null) {
            selectedGist = getArguments().getParcelable(GIST_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gist_detail, container, false);

        ArrayAdapter adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_dropdown_item, getFileNames());
        ((Spinner)view.findViewById(R.id.fileName)).setAdapter(adapter);
        initListeners(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectFile(fileIndexSelected);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.clearDisposable();
    }

    private void initListeners(View view){
        view.findViewById(R.id.homeButton).setOnClickListener(
                button -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, GistFragment.newInstance()).commit()
        );

        view.findViewById(R.id.backButton).setOnClickListener(
                button -> decrementSelectedFile()
        );

        view.findViewById(R.id.nextButton).setOnClickListener(
                button -> incrementSelectedFile()
        );

        ((Spinner)view.findViewById(R.id.fileName)).setOnItemSelectedListener(getFileSelectedListener());
    }

    @Override
    public void refreshFileContent(String fileContent) {
        ((TextView)getView().findViewById(R.id.fileContent)).setText(fileContent);
    }

    @Override
    public void error(String message) {
        Toast.makeText(getActivity(), message,
                Toast.LENGTH_LONG).show();
    }

    private void showFileContent(File file){
        //((TextView)getView().findViewById(R.id.fileName)).setText(file.getFilename());
        String url = file.getRaw_url();
        String filePath = url.substring(GithubFileClient.GITHUB_FILE_URL.length());
        Log.d(this.getClass().getSimpleName(), "Getting file content: " + filePath);
        presenter.getFileContent(filePath);
    }

    private void decrementSelectedFile(){
        if(fileIndexSelected - 1 >= 0){
            selectFile(--fileIndexSelected);
            ((Spinner)getView().findViewById(R.id.fileName)).setSelection(fileIndexSelected);
        }
    }

    private void incrementSelectedFile(){
        if(fileIndexSelected + 1 < getFiles().size()){
            selectFile(++fileIndexSelected);
            ((Spinner)getView().findViewById(R.id.fileName)).setSelection(fileIndexSelected);
        }
    }

    private void selectFile(int index){
        if(getFiles() !=null && !getFiles().isEmpty() ){
            showFileContent(getFiles().get(index));
        }
    }

    private List<String> getFileNames(){
        List<String> files = new ArrayList<>();
        for(File file: getFiles()){
            files.add(file.getFilename());
        }
        return files;
    }

    private List<File> getFiles(){
        return new ArrayList(selectedGist.getFiles().values());
    }

    private AdapterView.OnItemSelectedListener getFileSelectedListener(){
        return new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fileIndexSelected = i;
                selectFile(i);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        };
    }

}