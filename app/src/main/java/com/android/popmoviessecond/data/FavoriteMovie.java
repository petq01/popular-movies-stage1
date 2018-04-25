//package com.android.popmoviessecond.data;
//
///**
// * Created by Petya Marinova on 15-Apr-18.
// */
//
//public class FavoriteMovie {
//    public static final String TABLE_NAME = "favmovies";
//
//    public static final String COLUMN_ID = "id";
//    public static final String COLUMN_FAV_MOVIE = "favMovie";
//    public static final String COLUMN_TIMESTAMP = "timestamp";
//    public static final String COLUMN_ORIGINAL_TITLE = "originalTitle";
//    public static final String COLUMN_OVERVIEW = "overview";
//    public static final String COLUMN_USER_RATING = "userRating";
//    public static final String COLUMN_RELEASE_DATE = "releaseDate";
//    public static final String COLUMN_IMG_URL = "imgUrl";
//
//
//    private int id;
//    private String favMovie;
//    private String originalTitle;
//    private String overview;
//    private String userRating;
//    private String releaseDate;
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
//    public String getImgUrl() {
//        return imgUrl;
//    }
//
//    public void setImgUrl(String imgUrl) {
//        this.imgUrl = imgUrl;
//    }
//
//    private String imgUrl;
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getFavMovie() {
//        return favMovie;
//    }
//
//    public void setFavMovie(String favMovie) {
//        this.favMovie = favMovie;
//    }
//
//    public String getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public String getOriginalTitle() {
//
//        return originalTitle;
//    }
//
//    public void setOriginalTitle(String originalTitle) {
//        this.originalTitle = originalTitle;
//    }
//
//    private String timestamp;
//
//
//    // Create table SQL query
//    public static final String CREATE_TABLE =
//            "CREATE TABLE " + TABLE_NAME + "("
//                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + COLUMN_ORIGINAL_TITLE + " TEXT,"
//                    + COLUMN_FAV_MOVIE + " TEXT,"
//                    + COLUMN_OVERVIEW + " TEXT,"
//                    + COLUMN_RELEASE_DATE + " TEXT,"
//                    + COLUMN_USER_RATING + " TEXT,"
//                    + COLUMN_IMG_URL + " TEXT,"
//                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
//                    + ")";
//
//    public FavoriteMovie() {
//    }
//
//    public FavoriteMovie(int id, String favMovie, String originalTitle, String overview, String userRating, String releaseDate, String imgUrl, String timestamp) {
//        this.id = id;
//        this.favMovie = favMovie;
//        this.originalTitle = originalTitle;
//        this.overview = overview;
//        this.userRating = userRating;
//        this.releaseDate = releaseDate;
//        this.imgUrl = imgUrl;
//        this.timestamp = timestamp;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//}
