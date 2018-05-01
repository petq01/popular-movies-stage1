package com.android.popmoviessecond.fragments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.popmoviessecond.R;
import com.android.popmoviessecond.room.entities.FavMovieEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Petya Marinova on 18-Mar-18.
 */

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private List<FavMovieEntity> favMovieEntities;
    private Context context;

    public FavAdapter(List<FavMovieEntity> favMovieEntities, Context context) {
        this.favMovieEntities = favMovieEntities;
        this.context = context;
    }

    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_personal_collection, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FavMovieEntity favMovieEntity = favMovieEntities.get(position);
        holder.originalTitle.setText(favMovieEntity.getOriginalTitle());
        holder.overview.setText(favMovieEntity.getOverview());
        holder.userRating.setText(favMovieEntity.getUserRating());
        holder.releaseDate.setText(favMovieEntity.getReleaseDate());
        Picasso.with(context).load(favMovieEntity.getAvatarPath()).placeholder(R.drawable.ic_none).error(R.drawable.ic_error).into(holder.imageThumb);
        holder.overview.setText(favMovieEntity.getOverview());
        holder.userRating.setText(favMovieEntity.getUserRating());
        holder.releaseDate.setText(favMovieEntity.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return favMovieEntities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


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

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
