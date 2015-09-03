package com.example.mkoldobsky.popmovies;

/**
 * Created by mkoldobsky on 2/9/15.
 */
public class Movie {
    String title;
    String posterPath;
    private String plotSinapsys;
    private Double voteAverage;

    public Movie(String title, String posterPath, String plot, Double vote){

        this.title = title;
        this.posterPath = posterPath;
        plotSinapsys = plot;
        voteAverage = vote;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
