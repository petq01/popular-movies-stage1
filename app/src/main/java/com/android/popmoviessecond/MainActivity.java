package com.android.popmoviessecond;

import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.android.popmoviessecond.fragments.PostersFragment;
import com.android.popmoviessecond.room.provider.FavMovieCP;


public class MainActivity extends AppCompatActivity  {
    private static final int LOADER_FAVORITES= 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            goPostersFragment();
        }
//        getSupportLoaderManager().initLoader(LOADER_FAVORITES, null, mLoaderCallbacks);

    }
//    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks =
//            new LoaderManager.LoaderCallbacks<Cursor>() {
//
//                @Override
//                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//                    switch (id) {
//                        case LOADER_FAVORITES:
//                            return new CursorLoader(getApplicationContext(),
//                                    SampleContentProvider.URI_MOVIE,
//                                    new String[]{FavMovieCP.COLUMN_TITLE},
//                                    null, null, null);
//                        default:
//                            throw new IllegalArgumentException();
//                    }
//                }
//
//                @Override
//                public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//
//                }
//
//                @Override
//                public void onLoaderReset(Loader<Cursor> loader) {
//
//                }
//
//
//            };

    public void goPostersFragment(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_fragments, PostersFragment.newInstance(), PostersFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
//        super.onBackPressed();

    }
}
