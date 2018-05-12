package com.android.popmoviessecond.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.popmoviessecond.R;
import com.android.popmoviessecond.fragments.adapters.FavAdapter;
import com.android.popmoviessecond.room.provider.FavMovieCP;
import com.android.popmoviessecond.room.provider.SampleDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalFragment extends Fragment {
    @BindView(R.id.rv_personal)
    RecyclerView recyclerView;
    FavAdapter favAdapter;
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

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
            AsyncTask.execute(() -> {
                List<FavMovieCP> favMovieCPS = SampleDatabase.getInstance(getActivity()).favMovie().getAll();
                FavAdapter favAdapter = new FavAdapter(favMovieCPS, getActivity());
                favAdapter.notifyDataSetChanged();
                getActivity().runOnUiThread(() -> {
                    recyclerView.setAdapter(favAdapter);
                });
            });
    }
}


