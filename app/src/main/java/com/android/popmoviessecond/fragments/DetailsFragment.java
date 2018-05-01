package com.android.popmoviessecond.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.popmoviessecond.R;
import com.android.popmoviessecond.api.MovieAPI;
import com.android.popmoviessecond.api.model.Movie;
import com.android.popmoviessecond.api.model.response.VideoResponse;
import com.android.popmoviessecond.room.FavMovieDatabase;
import com.android.popmoviessecond.room.entities.FavMovieEntity;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
    @BindView(R.id.fav)
    Button btnFav;
    @BindView(R.id.unfav)
    Button btnUnFav;

//    FavMovieDatabase favMovieDatabase;

    public DetailsFragment() {
        // Required empty public constructor
    }

    private int mCounter;

    private static final String STATE_COUNTER = "counter";


    public static DetailsFragment newInstance(Movie movie) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("Movie", movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        favMovieDatabase = Room.databaseBuilder(getActivity(), FavMovieDatabase.class, FavMovieDatabase.DB_NAME).build();
        setRetainInstance(true);
        if (savedInstanceState != null) {
            mCounter = savedInstanceState.getInt(STATE_COUNTER, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, v);
        // Inflate the layout for this fragment

        Bundle bundle = getArguments();
        if (bundle != null) {

            movieBundle = bundle.getParcelable("Movie");
            originalTitle.setText(movieBundle.getOriginalTitle());
            //image thumb
            Picasso.with(getActivity()).load(movieBundle.getImageThumb()).placeholder(R.drawable.ic_none).error(R.drawable.ic_error).into(imageThumb);
            userRating.setText(String.format("%s/10", movieBundle.getUserRating().toString()));
            releaseDate.setText(movieBundle.getReleaseDate());
            overview.setText(movieBundle.getOverview());
            checkMovieFavorite();
        }


        return v;
    }

    public void checkMovieFavorite() {

        AsyncTask.execute(() -> {
            if (FavMovieDatabase.getAppDatabase(getActivity()).favMovieDao().findByOriginalTitle(movieBundle.getOriginalTitle()) == null) {
                getActivity().runOnUiThread(() -> {
                    btnUnFav.setVisibility(View.INVISIBLE);
                    btnFav.setVisibility(View.VISIBLE);
                });

            } else {
                getActivity().runOnUiThread(() -> {
                    btnUnFav.setVisibility(View.VISIBLE);
                    btnFav.setVisibility(View.INVISIBLE);
                });
            }
        });
    }

    @OnClick(R.id.fav)
    public void onAddClick() {
        FavMovieEntity favMovieEntity = new FavMovieEntity();
        favMovieEntity.setOriginalTitle(movieBundle.getOriginalTitle());
        favMovieEntity.setOverview(movieBundle.getOverview());
        favMovieEntity.setReleaseDate(movieBundle.getReleaseDate());
        favMovieEntity.setUserRating(movieBundle.getUserRating() + "/10");
        favMovieEntity.setAvatarPath(movieBundle.getImageThumb());
        AsyncTask.execute(() -> {
            FavMovieDatabase.getAppDatabase(getActivity()).favMovieDao().insert(favMovieEntity);
        });
        getActivity().runOnUiThread(() -> {
            btnUnFav.setVisibility(View.VISIBLE);
            btnFav.setVisibility(View.INVISIBLE);
        });


    }

    @OnClick(R.id.unfav)
    public void onRemoveClick() {

        AsyncTask.execute(() -> {
            FavMovieEntity favMovieEntity = FavMovieDatabase.getAppDatabase(getActivity()).favMovieDao().findByOriginalTitle(movieBundle.getOriginalTitle());
            FavMovieDatabase.getAppDatabase(getActivity()).favMovieDao().delete(favMovieEntity);
        });
        getActivity().runOnUiThread(() -> {
            btnUnFav.setVisibility(View.INVISIBLE);
            btnFav.setVisibility(View.VISIBLE);
        });


    }

    private MovieAPI createAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MovieAPI.BASE_URL)
                .build();

        return retrofit.create(MovieAPI.class);
    }

    @OnClick(R.id.trailerBtn)
    public void onClickTrailer() {
        Integer movie_id = movieBundle.getMovie_id();
        videosRequestPlayer(createAPI().getVideos(movie_id, MovieAPI.KEY));

    }

    @OnClick(R.id.youtube_trailer)
    public void onClickYoutubeApp() {
        Integer movie_id = movieBundle.getMovie_id();
        videosRequestYoutubeApp(createAPI().getVideos(movie_id, MovieAPI.KEY));
    }

    @OnClick(R.id.reviewBtn)
    public void onReadReviews() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_fragments, ReviewFragment.newInstance(movieBundle.getMovie_id()), ReviewFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void videosRequestPlayer(Observable<VideoResponse> videoResponse) {

        videoResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responses -> {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + responses.getMovieVideoResults().get(0).getKey()));
                    startActivity(intent);
                });
    }

    public void videosRequestYoutubeApp(Observable<VideoResponse> videoResponse) {

        videoResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responses -> {
                    String id = responses.getMovieVideoResults().get(0).getKey();

                    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + id));
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + id));
                    try {
                        startActivity(appIntent);
                    } catch (ActivityNotFoundException ex) {
                        startActivity(webIntent);
                    }
                });
    }

}
