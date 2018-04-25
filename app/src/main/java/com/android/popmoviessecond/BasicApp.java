package com.android.popmoviessecond;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.android.popmoviessecond.room.dao.FavMovieDatabase;

/**
 * Created by Petya Marinova on 22-Apr-18.
 */
public class BasicApp extends Application {

FavMovieDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(), FavMovieDatabase.class, "FavoriteMovies").build();
    }

}