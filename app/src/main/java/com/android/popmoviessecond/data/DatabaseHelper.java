//package com.android.popmoviessecond.data;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Petya Marinova on 15-Apr-18.
// */
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    // Database Version
//    private static final int DATABASE_VERSION = 1;
//
//    // Database Name
//    private static final String DATABASE_NAME = "FavoriteMovies_db";
//
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    // Creating Tables
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        // create FavoriteMovies table
//        db.execSQL(FavoriteMovie.CREATE_TABLE);
//    }
//
//    // Upgrading database
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + FavoriteMovie.TABLE_NAME);
//
//        // Create tables again
//        onCreate(db);
//    }
//
//
//
//    public long insertFavoriteMovie(String favMovie) {
//        // get writable database as we want to write data
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        // `id` and `timestamp` will be inserted automatically.
//        // no need to add them
//        values.put(FavoriteMovie.COLUMN_FAV_MOVIE, favMovie);
//
//        // insert row
//        long id = db.insert(FavoriteMovie.TABLE_NAME, null, values);
//
//        // close db connection
//        db.close();
//
//        // return newly inserted row id
//        return id;
//    }
//    public FavoriteMovie getFavoriteMovie(long id) {
//        // get readable database as we are not inserting anything
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(FavoriteMovie.TABLE_NAME,
//                new String[]{FavoriteMovie.COLUMN_ID, FavoriteMovie.COLUMN_FAV_MOVIE, FavoriteMovie.COLUMN_TIMESTAMP},
//                FavoriteMovie.COLUMN_ID + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        // prepare FavoriteMovie object
//        FavoriteMovie favoriteMovie = new FavoriteMovie(
//                cursor.getInt(cursor.getColumnIndex(FavoriteMovie.COLUMN_ID)),
//                cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_ORIGINAL_TITLE)),
//
//                cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_FAV_MOVIE)),
//                cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_OVERVIEW)),
//                cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_RELEASE_DATE)),
//                cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_USER_RATING)),
//                cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_IMG_URL)),
//                cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_TIMESTAMP)));
//
//        // close the db connection
//        cursor.close();
//
//        return favoriteMovie;
//    }
//
//    public List<FavoriteMovie> getAllFavoriteMovies() {
//        List<FavoriteMovie> favoriteMovies = new ArrayList<>();
//
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + FavoriteMovie.TABLE_NAME + " ORDER BY " +
//                FavoriteMovie.COLUMN_TIMESTAMP + " DESC";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                FavoriteMovie favoriteMovie = new FavoriteMovie();
//                favoriteMovie.setId(cursor.getInt(cursor.getColumnIndex(FavoriteMovie.COLUMN_ID)));
//                favoriteMovie.setOriginalTitle(cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_ORIGINAL_TITLE)));
//                favoriteMovie.setFavMovie(cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_FAV_MOVIE)));
//                favoriteMovie.setOverview(cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_OVERVIEW)));
//                favoriteMovie.setReleaseDate(cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_RELEASE_DATE)));
//                favoriteMovie.setUserRating(cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_USER_RATING)));
//
//                favoriteMovie.setImgUrl(cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_IMG_URL)));
//                favoriteMovie.setTimestamp(cursor.getString(cursor.getColumnIndex(FavoriteMovie.COLUMN_TIMESTAMP)));
//
//                favoriteMovies.add(favoriteMovie);
//            } while (cursor.moveToNext());
//        }
//
//        // close db connection
//        db.close();
//
//        // return FavoriteMovies list
//        return favoriteMovies;
//    }
//
//    public int getFavoriteMoviesCount() {
//        String countQuery = "SELECT  * FROM " + FavoriteMovie.TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//        int count = cursor.getCount();
//        cursor.close();
//
//
//        // return count
//        return count;
//    }
//
//    public int updateFavoriteMovie(FavoriteMovie favoriteMovie) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(FavoriteMovie.COLUMN_FAV_MOVIE, favoriteMovie.getFavMovie());
//
//        // updating row
//        return db.update(FavoriteMovie.TABLE_NAME, values, FavoriteMovie.COLUMN_ID + " = ?",
//                new String[]{String.valueOf(favoriteMovie.getId())});
//    }
//
//    public void deleteFavoriteMovie(FavoriteMovie favoriteMovie) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(FavoriteMovie.TABLE_NAME, FavoriteMovie.COLUMN_ID + " = ?",
//                new String[]{String.valueOf(favoriteMovie.getId())});
//        db.close();
//    }
//}