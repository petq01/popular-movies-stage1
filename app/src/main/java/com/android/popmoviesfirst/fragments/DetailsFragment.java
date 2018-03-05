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


public class DetailsFragment extends Fragment {

    Movie movieBundle;
    TextView originalTitle;
    TextView overview;
    TextView userRating;
    TextView releaseDate;
    ImageView imageThumb;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(Movie movie) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("Movie", movie);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        if (bundle != null) {

            movieBundle = (Movie) bundle.getSerializable("Movie");
        }

        originalTitle = v.findViewById(R.id.originalTitle);
        originalTitle.setText(movieBundle.getOriginalTitle());
        //image thumb
        imageThumb = v.findViewById(R.id.imageThumb);
        Picasso.with(getActivity()).load(movieBundle.getImageThumb()).into(imageThumb);
        userRating = v.findViewById(R.id.userRating);
        userRating.setText(movieBundle.getUserRating() + "/10");
        releaseDate = v.findViewById(R.id.releaseDate);
        releaseDate.setText(movieBundle.getReleaseDate());
        overview = v.findViewById(R.id.overview);
        overview.setText(movieBundle.getOverview());
        return v;
    }


}
