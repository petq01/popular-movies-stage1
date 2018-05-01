package com.android.popmoviessecond.fragments.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.popmoviessecond.R;
import com.android.popmoviessecond.api.model.ReviewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Petya Marinova on 18-Mar-18.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{

    private ArrayList<ReviewModel> reviewModels;

    public ReviewAdapter(ArrayList<ReviewModel> reviewModels) {
        this.reviewModels=reviewModels;
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_detail_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReviewModel reviewModel = reviewModels.get(position);
        holder.author.setText(reviewModel.author);
        holder.content.setText(reviewModel.content);
    }

    @Override
    public int getItemCount() {
        return reviewModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder  {


        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.content)
        TextView content;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
