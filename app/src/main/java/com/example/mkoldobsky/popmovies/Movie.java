package com.example.mkoldobsky.popmovies;

/**
 * Created by mkoldobsky on 2/9/15.
 */
public class Movie {
    String title;
    String posterPath;

    public Movie(String title, String posterPath){

        this.title = title;
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
