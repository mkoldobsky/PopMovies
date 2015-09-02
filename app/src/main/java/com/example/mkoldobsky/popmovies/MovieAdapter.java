package com.example.mkoldobsky.popmovies;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Movie> mMovies;

    public MovieAdapter(Context c, ArrayList<Movie> movies) {
        mContext = c;

        this.mMovies = movies;
    }

    public int getCount() {
        return mMovies.size();
    }

    public Object getItem(int position) {
        return mMovies.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        Uri builtUri = Uri.parse(" http://image.tmdb.org/t/p/w185").buildUpon()
                .appendPath("/" + mMovies.get(position).posterPath)
                .build();
        Picasso.with(mContext) //
                .load(builtUri.toString()) //
                .placeholder(R.drawable.placeholder) //
                .error(R.drawable.error) //
                .into(imageView);
        return imageView;
    }

}