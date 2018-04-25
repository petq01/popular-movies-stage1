package com.android.popmoviessecond;

/**
 * Created by Petya Marinova on 25-Mar-18.
 */

import io.realm.RealmObject;

import io.realm.annotations.PrimaryKey;

public class MovieFavorite extends  RealmObject {
    private Integer movieId;
    private String name;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
