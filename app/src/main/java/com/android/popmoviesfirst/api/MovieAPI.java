package com.android.popmoviesfirst.api;

import com.android.popmoviesfirst.api.model.response.MovieResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Petya Marinova on 27-Feb-18.
 */
public interface MovieAPI {
     String BASE_URL = "https://api.themoviedb.org/3/";
     String KEY = "";

    @GET("movie/popular?page=1&language=en-US")
    Observable<MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey
    );

    @GET("movie/top_rated?page=1&language=en-US")
    Observable<MovieResponse> getTopRated(
            @Query("api_key") String apiKey
    );
}