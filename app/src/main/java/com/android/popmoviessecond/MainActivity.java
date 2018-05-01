package com.android.popmoviessecond;

import android.app.FragmentTransaction;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.popmoviessecond.fragments.PostersFragment;
import com.android.popmoviessecond.room.FavMovieDatabase;


public class MainActivity extends AppCompatActivity {
FavMovieDatabase favMovieDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            goPostersFragment();
        }

    }
    public void goPostersFragment(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_fragments, PostersFragment.newInstance(), PostersFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        favMovieDatabase = Room.databaseBuilder(this, FavMovieDatabase.class, FavMovieDatabase.DB_NAME).build();
            goPostersFragment();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
    }
}
