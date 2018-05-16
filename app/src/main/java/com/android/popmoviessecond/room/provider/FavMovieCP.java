//package com.android.popmoviessecond.room.provider;
//
//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.PrimaryKey;
//import android.content.ContentValues;
//import android.provider.BaseColumns;
//
//
///**
// * Represents one record of the Cheese table.
// */
//@Entity(tableName = FavMovieCP.TABLE_NAME)
//public class FavMovieCP {
//
//    public static final String TABLE_NAME = "FavMovieCP";
//
//    public static final String COLUMN_ID = BaseColumns._ID;
//
//    public static final String COLUMN_TITLE = "originalTitle";
//    public static final String COLUMN_OVERVIEW = "overview";
//    public static final String COLUMN_RATING = "userRating";
//    public static final String COLUMN_RELEASE = "releaseDate";
//    public static final String COLUMN_AVATAR= "avatarPath";
//
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(index = true, name = COLUMN_ID)
//    public long uid;
//
//    @ColumnInfo(name = COLUMN_TITLE)
//    private String originalTitle;
//    @ColumnInfo(name = COLUMN_OVERVIEW)
//    private String overview;
//    @ColumnInfo(name = COLUMN_RATING)
//    private String userRating;
//    @ColumnInfo(name = COLUMN_RELEASE)
//    private String releaseDate;
//    @ColumnInfo(name = COLUMN_AVATAR)
//    private String avatarPath;
//    public static FavMovieCP fromContentValues(ContentValues values) {
//        final FavMovieCP favMovieCP = new FavMovieCP();
//        if (values.containsKey(COLUMN_ID)) {
//            favMovieCP.uid = values.getAsLong(COLUMN_ID);
//        }
//        if (values.containsKey(COLUMN_TITLE)) {
//            favMovieCP.originalTitle = values.getAsString(COLUMN_TITLE);
//        }
//        if (values.containsKey(COLUMN_OVERVIEW)) {
//            favMovieCP.overview = values.getAsString(COLUMN_OVERVIEW);
//        }
//        if (values.containsKey(COLUMN_RATING)) {
//            favMovieCP.userRating = values.getAsString(COLUMN_RATING);
//        }
//        if (values.containsKey(COLUMN_RELEASE)) {
//            favMovieCP.releaseDate = values.getAsString(COLUMN_RELEASE);
//        }
//        if (values.containsKey(COLUMN_AVATAR)) {
//            favMovieCP.avatarPath = values.getAsString(COLUMN_AVATAR);
//        }
//        return favMovieCP;
//    }
//
//    public String getAvatarPath() {
//        return avatarPath;
//    }
//
//    public void setAvatarPath(String avatarPath) {
//        this.avatarPath = avatarPath;
//    }
//
//
//    public long getUid() {
//        return uid;
//    }
//
//    public void setUid(long uid) {
//        this.uid = uid;
//    }
//
//    public String getOriginalTitle() {
//        return originalTitle;
//    }
//
//    public void setOriginalTitle(String originalTitle) {
//        this.originalTitle = originalTitle;
//    }
//
//    public String getOverview() {
//        return overview;
//    }
//
//    public void setOverview(String overview) {
//        this.overview = overview;
//    }
//
//    public String getUserRating() {
//        return userRating;
//    }
//
//    public void setUserRating(String userRating) {
//        this.userRating = userRating;
//    }
//
//    public String getReleaseDate() {
//        return releaseDate;
//    }
//
//    public void setReleaseDate(String releaseDate) {
//        this.releaseDate = releaseDate;
//    }
//
//}