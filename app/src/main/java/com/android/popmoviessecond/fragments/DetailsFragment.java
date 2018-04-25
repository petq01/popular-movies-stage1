package com.android.popmoviessecond.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.popmoviessecond.BasicApp;
import com.android.popmoviessecond.R;
import com.android.popmoviessecond.api.MovieAPI;
import com.android.popmoviessecond.api.model.Movie;
import com.android.popmoviessecond.api.model.response.VideoResponse;
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
    //    private Realm mRealm;
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
    BasicApp basicApp;


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
//        mRealm = Realm.getInstance(getContext());
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
        }


        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mRealm.close();
    }

    @OnClick(R.id.fav)
    public void onAddClick() {
        FavMovieEntity favMovieEntity = new FavMovieEntity();
        favMovieEntity.setOriginalTitle(movieBundle.getOriginalTitle());
        favMovieEntity.setOverview(movieBundle.getOverview());
        favMovieEntity.setReleaseDate(movieBundle.getReleaseDate());
        favMovieEntity.setUserRating(movieBundle.getUserRating() + "/10");
        favMovieEntity.setAvatarPath(movieBundle.getImageThumb());
        basicApp.database.favMovieDao().insert(favMovieEntity);
//        mRealm.beginTransaction();
//        MovieFavorite movieFavorite = mRealm.createObject(MovieFavorite.class);
//        movieFavorite.setMovieId(movieBundle.getMovie_id());
//        movieFavorite.setName(movieBundle.getOriginalTitle());
//        mRealm.commitTransaction();
//        realmHelper.save(movieFavorite);
    }

    @OnClick(R.id.unfav)
    public void onRemoveClick() {
        FavMovieEntity favMovieEntity = basicApp.database.favMovieDao().findByOriginalTitle(movieBundle.getOriginalTitle());
        basicApp.database.favMovieDao().delete(favMovieEntity);
//        mRealm.beginTransaction();
//        RealmResults<MovieFavorite> movieFavoriteRealmResults = mRealm.where(MovieFavorite.class).equalTo("name", movieBundle.getOriginalTitle()).findAll();
//        if (!movieFavoriteRealmResults.isEmpty()) {
//            for (int i = movieFavoriteRealmResults.size() - 1; i >= 0; i--) {
//                movieFavoriteRealmResults.get(i).removeFromRealm();
//            }
//        }
//        mRealm.commitTransaction();
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
