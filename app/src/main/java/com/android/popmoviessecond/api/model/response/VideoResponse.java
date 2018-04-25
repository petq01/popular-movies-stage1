
package com.android.popmoviessecond.api.model.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<VideoResult> movieVideoResults = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<VideoResult> getMovieVideoResults() {
        return movieVideoResults;
    }

    public void setMovieVideoResults(List<VideoResult> movieVideoResults) {
        this.movieVideoResults = movieVideoResults;
    }

}
