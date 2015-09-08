package com.example.mkoldobsky.popmovies.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable{
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

    public Movie(Parcel in) {
        title = in.readString();
        posterPath = in.readString();
        plotSynopsis = in.readString();
        voteAverage = in.readDouble();
        releaseDate = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
