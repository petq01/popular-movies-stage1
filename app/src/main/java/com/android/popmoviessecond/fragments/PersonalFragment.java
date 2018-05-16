package com.android.popmoviessecond.fragments;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.popmoviessecond.R;
import com.android.popmoviessecond.fragments.adapters.FavoritesAdapter;
import com.android.popmoviessecond.sqllite.FavoriteResult;
import com.android.popmoviessecond.sqllite.FavoritesProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalFragment extends Fragment {
    @BindView(R.id.rv_personal)
    RecyclerView recyclerView;
    public static int index = -1;
    public static int top = -1;
    LinearLayoutManager mLayoutManager;
//    private MovieDBHelper helper;
    private Cursor mCursor;
    FavoritesAdapter mAdapter;
    int mCurCheckPosition;
    public static PersonalFragment newInstance() {

        return new PersonalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal_collection, container, false);
        ButterKnife.bind(this, v);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
//        helper = new MovieDBHelper(getActivity());
//        sqlDB = helper.getReadableDatabase();
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
//        index = mLayoutManager.findFirstVisibleItemPosition();
//        View v = recyclerView.getChildAt(0);
//        top = (v == null) ? 0 : (v.getTop() - recyclerView.getPaddingTop());
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        refreshData();
//        if(index != -1)
//        {
//            mLayoutManager.scrollToPositionWithOffset( index, top);
//        }
//            AsyncTask.execute(() -> {
//                Cursor cursor = sqlDB.rawQuery("select * from "+ MoviesContract.FavMovieSQLEntry.TABLE_MOVIES,null);
//                MoviesContract.FavMovieSQLEntry favMovieSQLEntry=new MoviesContract.FavMovieSQLEntry();
//                List<MoviesContract.FavMovieSQLEntry> favMovieSQLEntries=new ArrayList<>();
//                if (cursor.moveToFirst()) {
//                    while (!cursor.isAfterLast()) {
//                        String name = cursor.getString(cursor.getColumnIndex(MoviesContract.FavMovieSQLEntry.COLUMN_TITLE));
//                        favMovieSQLEntry.;
//                        cursor.moveToNext();
//                    }
//                }
////                List<FavMovieCP> favMovieCPS = SampleDatabase.getInstance(getActivity()).favMovie().getAll();
//                FavAdapter favAdapter = new FavAdapter(favMovieCPS, getActivity());
//                favAdapter.notifyDataSetChanged();
//                getActivity().runOnUiThread(() -> {
//                    recyclerView.setAdapter(favAdapter);
//                });
//            });
    }
//    public List<MoviesContract.FavMovieSQLEntry> getAllNotes() {
//        List<MoviesContract.FavMovieSQLEntry> notes = new ArrayList<>();
//
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + MoviesContract.FavMovieSQLEntry.TABLE_MOVIES ;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                MoviesContract.FavMovieSQLEntry note = new MoviesContract.FavMovieSQLEntry();
//                note._ID=cursor.getInt(cursor.getColumnIndex(MoviesContract.FavMovieSQLEntry._ID));
//                note.setNote(cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)));
//                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));
//
//                notes.add(note);
//            } while (cursor.moveToNext());
//        }
//
//        // close db connection
//        db.close();
//
//        // return notes list
//        return notes;
//    }
private void retrieveFavData() {
    Uri movies = FavoritesProvider.PROVIDER_URI;
    if (mCursor != null) {
        mCursor.close();
    }
    mCursor = getActivity().getContentResolver().query(movies, null, null, null, null);
    if (mCursor != null && mCursor.getCount() > 0) {
        mCursor.moveToFirst();
        FavoriteResult.buildResult(mCursor);
    } else {
        Toast.makeText(getActivity()," No favorites yet",Toast.LENGTH_LONG ).show();
    }
}

    public void refreshData() {
        retrieveFavData();
        mAdapter = new FavoritesAdapter(mCursor,getActivity());
        recyclerView.setAdapter(mAdapter);
    }
}


