package com.android.popmoviesfirst.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Petya Marinova on 04-Mar-18.
 */

public class Movie implements Parcelable {
    private String originalTitle;
    private String imageThumb;
    private String overview;
    private Double userRating;
    private String releaseDate;


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            Movie movie = new Movie();
            movie.setOriginalTitle(in.readString());
            movie.setImageThumb(in.readString());
            movie.setOverview(in.readString());
            movie.setReleaseDate(in.readString());
            movie.setUserRating(in.readDouble());
            return movie;
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }


    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(originalTitle);
        dest.writeString(imageThumb);
        dest.writeString(overview);
        dest.writeDouble(userRating);
        dest.writeString(releaseDate);
    }
}
