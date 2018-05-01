package com.android.popmoviessecond.fragments.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.popmoviessecond.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Petya Marinova on 22-Feb-18.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> uriImage;

    // Constructor
    public ImageAdapter(Context c, ArrayList<String> uriImage) {
        mContext = c;
        this.uriImage = uriImage;
    }

    public int getCount() {
        return uriImage.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
//            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(mContext).load(uriImage.get(position)).placeholder(R.drawable.ic_none).error(R.drawable.ic_error).into(imageView);
        return imageView;
    }


}