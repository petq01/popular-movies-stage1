package com.android.popmoviessecond.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.popmoviessecond.R;
import com.android.popmoviessecond.api.MovieAPI;
import com.android.popmoviessecond.api.model.Movie;
import com.android.popmoviessecond.api.model.response.VideoResponse;
import com.android.popmoviessecond.sqllite.FavoritesProvider;
import com.android.popmoviessecond.sqllite.MoviesContract;
import com.android.popmoviessecond.sqllite.FavoriteResult;
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
    private boolean isFavorite = false;
    private FavoriteResult mMovie;
    int mCurCheckPosition;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            mMovie = new FavoriteResult(movieBundle.getImageThumb(), movieBundle.getOverview(), movieBundle.getReleaseDate(),
                    movieBundle.getMovie_id(), movieBundle.getOriginalTitle(), movieBundle.getUserRating());
            originalTitle.setText(movieBundle.getOriginalTitle());
            Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w185/" + movieBundle.getImageThumb()).placeholder(R.drawable.ic_none).error(R.drawable.ic_error).into(imageThumb);
            userRating.setText(String.format("%s/10", movieBundle.getUserRating().toString()));
            releaseDate.setText(movieBundle.getReleaseDate());
            overview.setText(movieBundle.getOverview());
        }


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkExistence();
        fillHeart();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }
    }
    private void checkExistence() {
        if (mMovie == null) {
            return;
        }
        Uri uri = Uri.withAppendedPath(FavoritesProvider.PROVIDER_URI, String.valueOf(mMovie.getId()));
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            isFavorite = cursor.getCount() > 0;
            cursor.close();
        }
    }

    @OnClick(R.id.fav)
    public void checkMovieFavorite() {
        if (getView() != null) {
            if (isFavorite) {
                removeFromFavorites();
            } else {
                addToFavorites();
            }
        }

        checkExistence();
        fillHeart();
    }

    private void fillHeart() {
        if (isFavorite) {
            btnFav.setBackgroundResource(R.drawable.ic_unfav);
        } else {
            btnFav.setBackgroundResource(R.drawable.ic_fav_heart);
        }
    }

    private void removeFromFavorites() {
        final ContentResolver contentResolver = getActivity().getContentResolver();
        final String[] selectionArgs = {String.valueOf(mMovie.getId())};
        final String columnMovieId = MoviesContract.FavoriteEntry.COLUMN_MOVIE_ID + " = ?";

        int nRows = contentResolver.delete(FavoritesProvider.PROVIDER_URI, columnMovieId, selectionArgs);
        if (nRows > 0) {
            Toast.makeText(getActivity(), " Removed", Toast.LENGTH_LONG).show();
        }
    }

    private void addToFavorites() {
        final ContentResolver contentResolver = getActivity().getContentResolver();
        final ContentValues values = MoviesContract.FavoriteEntry.resolveMovie(mMovie);

        Uri uri = contentResolver.insert(FavoritesProvider.PROVIDER_URI, values);
        if (uri != null) {
            Toast.makeText(getActivity(), "Liked ", Toast.LENGTH_LONG).show();
        }
    }
//
////            if (SampleDatabase.getInstance(getActivity()).favMovie().findByOriginalTitle(movieBundle.getOriginalTitle()) != null) {


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
