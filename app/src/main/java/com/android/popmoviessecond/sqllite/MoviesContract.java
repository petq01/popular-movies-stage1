package com.android.popmoviessecond.sqllite;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by Petya Marinova on 5/16/2018.
 */
public class MoviesContract {

    public static final String PROVIDER_AUTHORITY = "com.android.popmoviessecond";

    public static class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";

        public static final String DIR_BASE_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PROVIDER_AUTHORITY + "/" + TABLE_NAME;
        public static final String ITEM_BASE_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + PROVIDER_AUTHORITY + "/" + TABLE_NAME;

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_DATE = "release_date";
        public static final String COLUMN_MOVIE_ID = "movie_id";

        public static ContentValues resolveMovie(FavoriteResult movie) {
            ContentValues values = new ContentValues();

            values.put(COLUMN_TITLE, movie.getOriginalTitle());
            values.put(COLUMN_POSTER_PATH, movie.getPosterPath());
            values.put(COLUMN_OVERVIEW, movie.getOverview());
            values.put(COLUMN_MOVIE_ID, movie.getId());
            values.put(COLUMN_RATING, movie.getVoteAverage());
            values.put(COLUMN_DATE, movie.getReleaseDate());

            return values;
        }

    }
}