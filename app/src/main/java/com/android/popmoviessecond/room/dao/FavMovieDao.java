package com.android.popmoviessecond.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.android.popmoviessecond.room.entities.FavMovieEntity;

import java.util.List;

/**
 * Created by Petya Marinova on 22-Apr-18.
 */
@Dao
public interface FavMovieDao {
    @Query("SELECT * FROM FavMovieEntity")
    List<FavMovieEntity> getAll();

    @Query("SELECT * FROM FavMovieEntity WHERE originalTitle LIKE :originalTitle LIMIT 1")
    FavMovieEntity findByOriginalTitle(String originalTitle);

    @Insert
    void insertAll(List<FavMovieEntity> list);

    @Insert
    void insert(FavMovieEntity favMovieEntity);

    @Update
    void update(FavMovieEntity favMovieEntity);

    @Delete
    void delete(FavMovieEntity favMovieEntity);
}
