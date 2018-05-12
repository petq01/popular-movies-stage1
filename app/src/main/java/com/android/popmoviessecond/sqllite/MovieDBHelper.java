//package com.android.popmoviessecond.sqllite;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//public class MovieDBHelper extends SQLiteOpenHelper {
//    public static final String LOG_TAG = MovieDBHelper.class.getSimpleName();
//
//    //name & version
//    private static final String DATABASE_NAME = "movie.db";
//    private static final int DATABASE_VERSION = 12;
//
//    public MovieDBHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    // Create the database
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " +
//                MoviesContract.FavMovieSQLEntry.TABLE_MOVIES + "(" + MoviesContract.FavMovieSQLEntry._ID +
//                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                MoviesContract.FavMovieSQLEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
//                MoviesContract.FavMovieSQLEntry.COLUMN_OVERVIEW +
//                " TEXT NOT NULL, " +
//                MoviesContract.FavMovieSQLEntry.COLUMN_AVATAR +
//                " TEXT NOT NULL, "+
//                MoviesContract.FavMovieSQLEntry.COLUMN_RATING +
//                " TEXT NOT NULL, "+
//                MoviesContract.FavMovieSQLEntry.COLUMN_RELEASE +
//                " TEXT NOT NULL); ";
//
//        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
//    }
//
//    // Upgrade database when version is changed.
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to " +
//                newVersion + ". OLD DATA WILL BE DESTROYED");
//        // Drop the table
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.FavMovieSQLEntry.TABLE_MOVIES);
//        sqLiteDatabase.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
//                MoviesContract.FavMovieSQLEntry.TABLE_MOVIES + "'");
//
//        // re-create database
//        onCreate(sqLiteDatabase);
//    }
//}