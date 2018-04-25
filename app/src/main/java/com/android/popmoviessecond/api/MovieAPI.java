package com.android.popmoviessecond.api;

import com.android.popmoviessecond.api.model.response.MovieResponse;
import com.android.popmoviessecond.api.model.response.ReviewResponse;
import com.android.popmoviessecond.api.model.response.VideoResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Petya Marinova on 27-Feb-18.
 */
public interface MovieAPI {
     String BASE_URL = "https://api.themoviedb.org/3/";
     String KEY = "e58dd8463abbef0fff6a77e447d71357";

    @GET("movie/popular?page=1&language=en-US")
    Observable<MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey
    );

    @GET("movie/top_rated?page=1&language=en-US")
    Observable<MovieResponse> getTopRated(
            @Query("api_key") String apiKey
    );
    @GET("movie/{movie_id}/videos")
    Observable<VideoResponse> getVideos(
            @Path("movie_id") Integer movie_id,
            @Query("api_key") String apiKey
    );
    @GET("movie/{movie_id}/reviews?page=1")
    Observable<ReviewResponse> getReviews(
            @Path("movie_id") Integer movie_id,
            @Query("api_key") String apiKey
    );

}