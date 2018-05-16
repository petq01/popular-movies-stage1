package com.android.popmoviessecond.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.popmoviessecond.R;
import com.android.popmoviessecond.api.MovieAPI;
import com.android.popmoviessecond.api.model.ReviewModel;
import com.android.popmoviessecond.api.model.response.ReviewResponse;
import com.android.popmoviessecond.api.model.response.ReviewResult;
import com.android.popmoviessecond.fragments.adapters.ReviewAdapter;
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
 * Created by Petya Marinova on 18-Mar-18.
 */

public class ReviewFragment extends Fragment {
    @BindView(R.id.rv_reviews)
    RecyclerView recyclerView;
    Integer movie_id;
    private ReviewAdapter reviewAdapter;
    public static int index = -1;
    public static int top = -1;
    LinearLayoutManager mLayoutManager;
    int mCurCheckPosition;
    public ReviewFragment() {

    }

    public static ReviewFragment newInstance(Integer movie_id) {

        Bundle args = new Bundle();
        args.putInt("movieId", movie_id);
        ReviewFragment fragment = new ReviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this, v);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        Bundle bundle = getArguments();
        movie_id = bundle.getInt("movieId");
        reviewsRequest(createAPI().getReviews(movie_id, MovieAPI.KEY));
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
//        index = mLayoutManager.findFirstVisibleItemPosition();
//        View v = recyclerView.getChildAt(0);
//        top = (v == null) ? 0 : (v.getTop() - recyclerView.getPaddingTop());
    }

    @Override
    public void onResume() {
        super.onResume();
//        if(index != -1)
//        {
//            mLayoutManager.scrollToPositionWithOffset( index, top);
//        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }
    }
    public void reviewsRequest(Observable<ReviewResponse> reviewResponseObservable) {

        reviewResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responses -> {
                    ArrayList<ReviewModel> reviewModels = new ArrayList<>();
                    for (ReviewResult reviewResponse : responses.getResults()) {
                        ReviewModel reviewModel = new ReviewModel();
                        reviewModel.author = reviewResponse.getAuthor();
                        reviewModel.content = reviewResponse.getContent();
                        reviewModels.add(reviewModel);
                    }
                    reviewAdapter = new ReviewAdapter(reviewModels);
                    recyclerView.setAdapter(reviewAdapter);
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
}
