package com.android.popmoviessecond.fragments.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.popmoviessecond.R;
import com.android.popmoviessecond.sqllite.FavoriteResult;
import com.android.popmoviessecond.sqllite.MoviesContract;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Petya Marinova on 5/16/2018.
 */
public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private CursorAdapter mCursorAdapter;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
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
        public FavoriteResult result;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    public FavoritesAdapter(Cursor cursor, Context context) {
        mCursorAdapter = new CursorAdapter(context, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return inflater.inflate(R.layout.item_personal_collection, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView txtTitle = view.findViewById(R.id.originalTitle);
                txtTitle.setText(cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_TITLE)));

                TextView txtDescription = (TextView) view.findViewById(R.id.overview);
                txtDescription.setText(cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_OVERVIEW)));

                TextView txtPopularity = (TextView) view.findViewById(R.id.releaseDate);
                String columnDate = cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_DATE));
                txtPopularity.setText("Release date: "+ columnDate);

                TextView txtRating = (TextView) view.findViewById(R.id.userRating);
                Float ratingFloat = Float.valueOf(cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_RATING)));
                txtRating.setText(String.format("Rating: %.2f", ratingFloat));

                final ImageView imgPath = (ImageView) view.findViewById(R.id.imageThumb);
                Picasso.with(context).load("http://image.tmdb.org/t/p/w185"+ cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_POSTER_PATH))).placeholder(R.drawable.ic_none).error(R.drawable.ic_error).into(imgPath);
            }
        };
    }

    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mCursorAdapter.newView(mContext, mCursorAdapter.getCursor(), parent);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FavoritesAdapter.ViewHolder viewHolder, int position) {
        Cursor cursor = mCursorAdapter.getCursor();

        cursor.moveToPosition(position);
        viewHolder.result = FavoriteResult.buildResult(cursor);
        mCursorAdapter.bindView(viewHolder.itemView, mContext, cursor);
    }

    @Override
    public int getItemCount() {
        return mCursorAdapter.getCount();
    }
}
