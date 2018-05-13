package com.android.popmoviessecond.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.android.popmoviessecond.room.provider.FavMovieCP;
import com.android.popmoviessecond.room.provider.SampleContentProvider;
import com.android.popmoviessecond.room.provider.SampleDatabase;
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


public class DetailsFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {
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

    private int detailId;


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
        setRetainInstance(true);
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

            Cursor originalTitle = getActivity().getContentResolver().query(
                    SampleContentProvider.URI_MOVIE,
                    new String[]{FavMovieCP.COLUMN_TITLE},
                    "originalTitle = ? ",
                    new String[]{movieBundle.getOriginalTitle()},
                    null);
            detailId = originalTitle.getColumnIndexOrThrow(FavMovieCP.COLUMN_TITLE);
//            originalTitle.close();
//            if (detailId != -1) {
            if (SampleDatabase.getInstance(getActivity()).favMovie().findByOriginalTitle(movieBundle.getOriginalTitle()) != null) {


                getActivity().runOnUiThread(() -> {
                    btnUnFav.setVisibility(View.VISIBLE);
                    btnFav.setVisibility(View.GONE);
                });
            } else {
                getActivity().runOnUiThread(() -> {
                    btnUnFav.setVisibility(View.GONE);
                    btnFav.setVisibility(View.VISIBLE);
                });
            }
        });
    }

    @OnClick(R.id.fav)
    public void onAddClick() {
            AsyncTask.execute(() -> {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(FavMovieCP.COLUMN_TITLE, movieBundle.getOriginalTitle());
                    contentValues.put(FavMovieCP.COLUMN_OVERVIEW, movieBundle.getOverview());
                    contentValues.put(FavMovieCP.COLUMN_RELEASE, movieBundle.getReleaseDate());
                    contentValues.put(FavMovieCP.COLUMN_RATING, movieBundle.getUserRating() + "/10");
                    contentValues.put(FavMovieCP.COLUMN_AVATAR, movieBundle.getImageThumb());
                    getActivity().getContentResolver().insert(SampleContentProvider.URI_MOVIE, contentValues);
                    getActivity().runOnUiThread(() -> {
                        btnUnFav.setVisibility(View.VISIBLE);
                        btnFav.setVisibility(View.GONE);
                    });

            });


    }

    @OnClick(R.id.unfav)
    public void onRemoveClick() {

        AsyncTask.execute(() -> {
            FavMovieCP favMovieCP = SampleDatabase.getInstance(getActivity()).favMovie().findByOriginalTitle(movieBundle.getOriginalTitle());
            SampleDatabase.getInstance(getActivity()).favMovie().delete(favMovieCP);
            getActivity().runOnUiThread(() -> {
                btnUnFav.setVisibility(View.GONE);
                btnFav.setVisibility(View.VISIBLE);
            });
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                FavMovieCP.COLUMN_ID,
                FavMovieCP.COLUMN_RELEASE,
                FavMovieCP.COLUMN_AVATAR,
                FavMovieCP.COLUMN_TITLE,
                FavMovieCP.COLUMN_RATING,
                FavMovieCP.COLUMN_OVERVIEW};
        return new CursorLoader(getActivity(),
                SampleContentProvider.URI_MOVIE, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
