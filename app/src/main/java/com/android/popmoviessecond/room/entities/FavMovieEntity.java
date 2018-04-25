package com.android.popmoviessecond.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Petya Marinova on 22-Apr-18.
 */

@Entity
public class FavMovieEntity  {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "originalTitle")
    private String originalTitle;
    @ColumnInfo(name = "overview")
    private String overview;
    @ColumnInfo(name = "userRating")
    private String userRating;
    @ColumnInfo(name = "releaseDate")
    private String releaseDate;

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    @ColumnInfo(name = "avatarPath")
    private String avatarPath;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
