package com.example.mkoldobsky.popmovies;

import android.os.Bundle;

/**
 * Created by mkoldobsky on 2/9/15.
 */
public class Movie {
    private String title;
    private String posterPath;
    private String plotSynopsis;
    private Double voteAverage;
    private String releaseDate;

    final static String TITTLE_KEY = "title";
    final static String PATH_KEY = "path";
    final static String PLOT_KEY = "plot";
    final static String VOTE_KEY = "vote";
    final static String DATE_KEY = "date";

    public Movie(String title, String posterPath, String plot, Double vote, String date){

        this.title = title;
        this.posterPath = posterPath;
        this.plotSynopsis = plot;
        this.voteAverage = vote;
        this.releaseDate = date;
    }

    public Movie (Bundle bundle){
        if (bundle != null) {
            this.title = bundle.getString(TITTLE_KEY);
            this.posterPath = bundle.getString(PATH_KEY);
            this.plotSynopsis = bundle.getString(PLOT_KEY);
            this.voteAverage = bundle.getDouble(VOTE_KEY);
            this.releaseDate = bundle.getString(DATE_KEY);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putString(TITTLE_KEY, this.title);
        bundle.putString(PATH_KEY, this.posterPath);
        bundle.putString(PLOT_KEY, this.plotSynopsis);
        bundle.putDouble(VOTE_KEY, this.voteAverage);
        bundle.putString(DATE_KEY, this.releaseDate);
        return bundle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
