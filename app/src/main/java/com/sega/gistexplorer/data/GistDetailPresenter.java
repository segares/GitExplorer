package com.sega.gistexplorer.data;

import android.util.Log;

import com.sega.gistexplorer.view.GistDetailView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GistDetailPresenter {

    private GistDetailView view;
    private CompositeDisposable compositeDisposable;

    public GistDetailPresenter(GistDetailView view){
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    public void getFileContent(String filePath){
        Log.d(this.getClass().getSimpleName(), "Getting file content: " + filePath);
        ApiGistService gistApi = GithubFileClient.getInstance().create(ApiGistService.class);

        compositeDisposable.add(gistApi.getFile(filePath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        fileContent -> {
                            if(fileContent != null){
                                view.refreshFileContent(fileContent);
                            }
                        },
                        error -> {
                            view.error(error.getMessage());
                            Log.e(this.getClass().getSimpleName(), error.getMessage());
                        }
                ));
    }

    public void clearDisposable(){
        compositeDisposable.clear();
    }

}
