package com.android.popmoviessecond.room.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.android.popmoviessecond.room.entities.FavMovieEntity;

/**
 * Created by Petya Marinova on 22-Apr-18.
 */
@Database(entities = {FavMovieEntity.class}, version = 1)
public abstract class FavMovieDatabase extends RoomDatabase {
    public abstract FavMovieDao favMovieDao();
}