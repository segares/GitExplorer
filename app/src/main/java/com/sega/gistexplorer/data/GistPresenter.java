package com.sega.gistexplorer.data;

import android.util.Log;

import com.sega.gistexplorer.view.GistView;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GistPresenter {

    private GistView view;
    private CompositeDisposable compositeDisposable;
    private static final int UPDATE_GIST_JOB_TIMER = 15;

    public GistPresenter(GistView view){
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    public void startScheduledJob(){
        Log.d("GistPresenter:", "Starting job.");
        compositeDisposable.add(Observable
                .interval(UPDATE_GIST_JOB_TIMER, TimeUnit.MINUTES)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        repeat -> fetchGists(),
                        error -> Log.e("Error:", error.getMessage())
                ));
    }

    public void fetchGists(){
        Log.d("GistPresenter:", "Fetching Gist.");
        ApiGistService gistApi = GistClient.getInstance().create(ApiGistService.class);

        compositeDisposable.add(gistApi.getPublicGists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        gists -> {
                            if(gists != null && !gists.isEmpty()){
                                Collections.sort(gists, Collections.reverseOrder());
                                view.refreshGistTable(gists);
                            }
                        },
                        error -> {
                            view.error(error.getMessage());
                            Log.e("Error:", error.getMessage());
                        }
                ));
    }

    public void clearDisposable(){
        compositeDisposable.clear();
    }

}
