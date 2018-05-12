//package com.android.popmoviessecond.sqllite;
//
//import android.content.ContentResolver;
//import android.content.ContentUris;
//import android.net.Uri;
//import android.provider.BaseColumns;
//
//public class MoviesContract {
//
//    public static final String CONTENT_AUTHORITY = "com.android.popmoviessecond";
//
//    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
//
//
//    public static final class FavMovieSQLEntry implements BaseColumns {
//        // table name
//        public static final String TABLE_MOVIES = "FavMovieSQLEntry";
//        // columns
//        public static final String _ID = "_id";
//        public static final String COLUMN_TITLE = "originalTitle";
//        public static final String COLUMN_OVERVIEW = "overview";
//        public static final String COLUMN_RATING = "userRating";
//        public static final String COLUMN_RELEASE = "releaseDate";
//        public static final String COLUMN_AVATAR = "avatarPath";
//
//        // create content uri
//        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
//                .appendPath(TABLE_MOVIES).build();
//        // create cursor of base type directory for multiple entries
//        public static final String CONTENT_DIR_TYPE =
//                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_MOVIES;
//        // create cursor of base type item for single entry
//        public static final String CONTENT_ITEM_TYPE =
//                ContentResolver.CURSOR_ITEM_BASE_TYPE +"/" + CONTENT_AUTHORITY + "/" + TABLE_MOVIES;
//
//        // for building URIs on insertion
//        public static Uri buildMoviesUri(long id){
//            return ContentUris.withAppendedId(CONTENT_URI, id);
//        }
//    }
//}