package com.android.popmoviessecond.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Petya Marinova on 22-Apr-18.
 */

public class FavoritesFragment extends Fragment {
    public FavoritesFragment(){

    }

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
return v;
    }
}
