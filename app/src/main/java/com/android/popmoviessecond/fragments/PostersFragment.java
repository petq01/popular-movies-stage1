package com.android.popmoviessecond.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.popmoviessecond.fragments.adapters.ImageAdapter;
import com.android.popmoviessecond.api.MovieAPI;
import com.android.popmoviessecond.R;
import com.android.popmoviessecond.api.model.Movie;
import com.android.popmoviessecond.api.model.response.MovieResponse;
import com.android.popmoviessecond.api.model.response.MovieResult;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Petya Marinova on 04-Mar-18.
 */

public class PostersFragment extends Fragment {
    @BindView(R.id.gridview)
    GridView gridview;

    Movie movie;

    public PostersFragment() {

    }


    public static PostersFragment newInstance() {


        PostersFragment fragment = new PostersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_posters, container, false);
        ButterKnife.bind(this, v);
        moviesRequest(createAPI().getPopularMovies(MovieAPI.KEY));
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu, menu);
    }

    private MovieAPI createAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MovieAPI.BASE_URL)
                .build();

        return retrofit.create(MovieAPI.class);
    }

    public void moviesRequest(Observable<MovieResponse> movieResponse) {

        movieResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responses -> {
                    ArrayList<String> images = new ArrayList<>();
                    for (MovieResult img : responses.movieResults) {
                        images.add("http://image.tmdb.org/t/p/w185/" + img.posterPath);

                    }
                    gridview.setAdapter(new ImageAdapter(getActivity(), images));
                    gridview.setOnItemClickListener((parent, v, position, id) -> {
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        movie = new Movie();
                        movie.setMovie_id(responses.movieResults.get(position).id);
                        movie.setOriginalTitle(responses.movieResults.get(position).originalTitle);
                        movie.setOverview(responses.movieResults.get(position).overview);
                        movie.setReleaseDate(responses.movieResults.get(position).releaseDate);
                        movie.setUserRating(responses.movieResults.get(position).voteAverage);
                        movie.setImageThumb("http://image.tmdb.org/t/p/w185/" + responses.movieResults.get(position).posterPath);
                        fragmentTransaction.replace(R.id.container_fragments, DetailsFragment.newInstance(movie), DetailsFragment.class.getSimpleName());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    });
                });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.popular_movies:
                moviesRequest(createAPI().getPopularMovies(MovieAPI.KEY));
                return true;
            case R.id.rated:
                moviesRequest(createAPI().getTopRated(MovieAPI.KEY));
                return true;
            case R.id.personal:
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_fragments, PersonalFragment.newInstance(), PersonalFragment.class.getSimpleName());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
