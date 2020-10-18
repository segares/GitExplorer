package com.sega.gistexplorer.data;

import com.sega.gistexplorer.model.Gist;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiGistService {

    @GET("public")
    Observable<List<Gist>> getPublicGists();

    @GET("/{filePath}")
    Observable<String> getFile(@Path(value = "filePath", encoded = true)String filePath);
}
