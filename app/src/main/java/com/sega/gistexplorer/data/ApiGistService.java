package com.sega.gistexplorer.data;

import com.sega.gistexplorer.model.Gist;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiGistService {

    @GET("public")
    Observable<List<Gist>> getPublicGists();
}
