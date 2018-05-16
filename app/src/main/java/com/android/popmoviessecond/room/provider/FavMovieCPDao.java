//package com.android.popmoviessecond.room.provider;
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Delete;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.Query;
//import android.arch.persistence.room.Update;
//import android.database.Cursor;
//
//
//import java.util.List;
//
//
///**
// * Data access object for FavMovieCP.
// */
//@Dao
//public interface FavMovieCPDao {
//
//    /**
//     * Counts the number of FavMovieCPs in the table.
//     *
//     * @return The number of FavMovieCPs.
//     */
//    @Query("SELECT COUNT(*) FROM " + FavMovieCP.TABLE_NAME)
//    int count();
//
//    /**
//     * Inserts a FavMovieCP into the table.
//     *
//     * @param favMovieCP A new FavMovieCP.
//     * @return The row ID of the newly inserted FavMovieCP.
//     */
//    @Insert
//    long insert(FavMovieCP favMovieCP);
//
//    /**
//     * Inserts multiple FavMovieCPs into the database
//     *
//     * @param favMovieCPs An array of new FavMovieCPs.
//     * @return The row IDs of the newly inserted FavMovieCPs.
//     */
//    @Insert
//    long[] insertAll(FavMovieCP[] favMovieCPs);
//
//    /**
//     * Select all FavMovieCPs.
//     *
//     * @return A {@link Cursor} of all the FavMovieCPs in the table.
//     */
//    @Query("SELECT * FROM " + FavMovieCP.TABLE_NAME)
//    Cursor selectAll();
//
//    /**
//     * Select a FavMovieCP by the ID.
//     *
//     * @param id The row ID.
//     * @return A {@link Cursor} of the selected FavMovieCP.
//     */
//    @Query("SELECT * FROM " + FavMovieCP.TABLE_NAME + " WHERE " + FavMovieCP.COLUMN_ID + " = :id")
//    Cursor selectById(long id);
//    @Query("SELECT * FROM " + FavMovieCP.TABLE_NAME + "  WHERE " + FavMovieCP.COLUMN_TITLE + " = :originalTitle LIMIT 1")
//    FavMovieCP findByOriginalTitle(String originalTitle);
//
//    /**
//     * Delete a FavMovieCP by the ID.
//     *
//     * @param id The row ID.
//     * @return A number of FavMovieCPs deleted. This should always be {@code 1}.
//     */
//    @Query("DELETE FROM " + FavMovieCP.TABLE_NAME + " WHERE " + FavMovieCP.COLUMN_ID + " = :id")
//    int deleteById(long id);
//
//    @Delete
//    void delete(FavMovieCP favMovieCP);
//    @Query("SELECT * FROM " + FavMovieCP.TABLE_NAME )
//    List<FavMovieCP> getAll();
//    /**
//     * Update the FavMovieCP. The FavMovieCP is identified by the row ID.
//     *
//     * @param FavMovieCP The FavMovieCP to update.
//     * @return A number of FavMovieCPs updated. This should always be {@code 1}.
//     */
//    @Update
//    int update(FavMovieCP FavMovieCP);
//
//}