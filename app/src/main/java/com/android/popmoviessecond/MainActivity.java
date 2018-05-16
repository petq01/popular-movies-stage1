package com.android.popmoviessecond;

import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.android.popmoviessecond.fragments.PostersFragment;


public class MainActivity extends AppCompatActivity  {
    Parcelable mListState;
    LinearLayoutManager mLayoutManager;
    String LIST_STATE_KEY="RV";
    int mCurCheckPosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            goPostersFragment();
        }

    }
//    protected void onSaveInstanceState(Bundle state) {
//        super.onSaveInstanceState(state);
//
//        // Save list state
//        mListState = mLayoutManager.onSaveInstanceState();
//        state.putParcelable(LIST_STATE_KEY, mListState);
//    }
//    protected void onRestoreInstanceState(Bundle state) {
//        super.onRestoreInstanceState(state);
//
//        // Retrieve list state and list/item positions
//        if(state != null)
//            mListState = state.getParcelable(LIST_STATE_KEY);
//    }
@Override
public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("curChoice", mCurCheckPosition);
}
    @Override
    protected void onResume() {
        super.onResume();

//        if (mListState != null) {
//            mLayoutManager.onRestoreInstanceState(mListState);
//        }
    }
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
