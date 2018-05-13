package com.android.popmoviessecond.fragments.adapters;


import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.popmoviessecond.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Petya Marinova on 22-Feb-18.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>  {
    private Context mContext;
    private ArrayList<String> uriImage;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // Constructor
    public ImageAdapter(Context c, ArrayList<String> uriImage) {
        this.mInflater = LayoutInflater.from(c);
        mContext = c;
        this.uriImage = uriImage;
    }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = mInflater.inflate(R.layout.item_poster, parent, false);
    return new ViewHolder(view);
}

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(uriImage.get(position)).placeholder(R.drawable.ic_none).error(R.drawable.ic_error).into(holder.poster);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return uriImage.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_poster)
        ImageView poster;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return uriImage.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}