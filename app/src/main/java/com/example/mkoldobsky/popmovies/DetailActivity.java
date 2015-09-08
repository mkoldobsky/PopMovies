package com.example.mkoldobsky.popmovies;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mkoldobsky.popmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    Movie mMovie;
    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle movieBundle = getIntent().getExtras();
        mMovie = new Movie(movieBundle);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mMovie.getTitle());

        viewHolder = new ViewHolder();
        viewHolder.titleTextView = (TextView)findViewById(R.id.movie_title_textview);
        viewHolder.moviePosterImageView = (ImageView)findViewById(R.id.movie_poster_imageview);
        viewHolder.plotSynopsisTextView = (TextView) findViewById(R.id.plot_synopsis_textview);
        viewHolder.releaseDateTextView = (TextView)findViewById(R.id.release_date_textview);
        viewHolder.userRatingBar = (RatingBar)findViewById(R.id.user_rating_bar);

        updateDetails();
        loadImages();
    }

    private void updateDetails() {
        viewHolder.titleTextView.setText(mMovie.getTitle());
        viewHolder.plotSynopsisTextView.setText(this.getText(R.string.not_available));
        viewHolder.releaseDateTextView.setText(this.getText(R.string.not_available));
        if (mMovie.getPlotSynopsis() != null && !mMovie.getPlotSynopsis().equals("null")) {
            viewHolder.plotSynopsisTextView.setText(mMovie.getPlotSynopsis());
        }
        if (mMovie.getReleaseDate() != null && !mMovie.getReleaseDate().equals("null")) {
            viewHolder.releaseDateTextView.setText(mMovie.getReleaseDate());
        }
        viewHolder.userRatingBar.setRating(mMovie.getVoteAverage().floatValue() / 2);
    }

    private void loadImages() {
        final ImageView imageView = (ImageView) findViewById(R.id.movie_poster);
        Uri builtUri = Uri.parse("http://image.tmdb.org/t/p/w185" + mMovie.getPosterPath()).buildUpon()
                .build();
        Picasso.with(this).load(builtUri.toString()).into(imageView);
        Picasso.with(this).load(builtUri.toString()).into(viewHolder.moviePosterImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView moviePosterImageView;
        TextView plotSynopsisTextView;
        TextView releaseDateTextView;
        RatingBar userRatingBar;

    }
}
