
package com.android.popmoviessecond.api.model.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("results")
    @Expose
    public List<MovieResult> movieResults;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;

}
