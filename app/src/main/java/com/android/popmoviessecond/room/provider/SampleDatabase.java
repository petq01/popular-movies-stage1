//package com.android.popmoviessecond.room.provider;
//
//import android.arch.persistence.room.Database;
//import android.arch.persistence.room.Room;
//import android.arch.persistence.room.RoomDatabase;
//import android.content.Context;
//import android.support.annotation.VisibleForTesting;
//
//
//
///**
// * The Room database.
// */
//@Database(entities = {FavMovieCP.class}, version = 2)
//public abstract class SampleDatabase extends RoomDatabase {
//
//    /**
//     * @return The DAO for the Cheese table.
//     */
//    @SuppressWarnings("WeakerAccess")
//    public abstract FavMovieCPDao favMovie();
//
//    /** The only instance */
//    private static SampleDatabase sInstance;
//
//    /**
//     * Gets the singleton instance of SampleDatabase.
//     *
//     * @param context The context.
//     * @return The singleton instance of SampleDatabase.
//     */
//    public static synchronized SampleDatabase getInstance(Context context) {
//        if (sInstance == null) {
//            sInstance = Room
//                    .databaseBuilder(context.getApplicationContext(), SampleDatabase.class, "ex")
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return sInstance;
//    }
//
//    /**
//     * Switches the internal implementation with an empty in-memory database.
//     *
//     * @param context The context.
//     */
//    @VisibleForTesting
//    public static void switchToInMemory(Context context) {
//        sInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
//                SampleDatabase.class).build();
//    }
//
//
//}