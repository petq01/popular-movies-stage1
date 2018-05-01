package com.android.popmoviessecond.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.android.popmoviessecond.room.dao.FavMovieDao;
import com.android.popmoviessecond.room.entities.FavMovieEntity;

/**
 * Created by Petya Marinova on 22-Apr-18.
 */
@Database(entities = {FavMovieEntity.class}, version = 1)
public abstract class FavMovieDatabase extends RoomDatabase {
    public abstract FavMovieDao favMovieDao();
    public static final String DB_NAME="FavMovieDatabase";
    private static FavMovieDatabase INSTANCE;


    public static FavMovieDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), FavMovieDatabase.class, DB_NAME)
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}