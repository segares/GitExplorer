package com.sega.gistexplorer.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GistClient {

    private static Retrofit retrofitInstance;

    public static Retrofit getInstance() {
        if (retrofitInstance == null)
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/gists/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return retrofitInstance;
    }

    private GistClient() {}

}
