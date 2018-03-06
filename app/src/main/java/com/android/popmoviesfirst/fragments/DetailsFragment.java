package com.android.popmoviesfirst.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.popmoviesfirst.R;
import com.android.popmoviesfirst.api.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsFragment extends Fragment {

    Movie movieBundle;
    @BindView(R.id.originalTitle)
    TextView originalTitle;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.userRating)
    TextView userRating;
    @BindView(R.id.releaseDate)
    TextView releaseDate;
    @BindView(R.id.imageThumb)
    ImageView imageThumb;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(Movie movie) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("Movie", movie);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this,v);
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        if (bundle != null) {

            movieBundle = (Movie) bundle.getParcelable("Movie");
        }

        originalTitle.setText(movieBundle.getOriginalTitle());
        //image thumb
        Picasso.with(getActivity()).load(movieBundle.getImageThumb()).placeholder(R.drawable.ic_none).error(R.drawable.ic_error).into(imageThumb);
        userRating.setText(movieBundle.getUserRating() + "/10");
        releaseDate.setText(movieBundle.getReleaseDate());
        overview.setText(movieBundle.getOverview());
        return v;
    }


}
