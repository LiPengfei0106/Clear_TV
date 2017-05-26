package com.lipengfei.tv.bean;

import java.util.List;

/**
 * Created by Lee on 2017/5/24.
 */

public class MoviesJson {

    private List<Movie> Movies;
    private List<MovieTag> Tags;
    private List<MovieLocation> Location;
    private MoviesJson MovieInfo;

    public List<Movie> getMovies() {
        return Movies;
    }

    public List<MovieTag> getTags() {
        return Tags;
    }

    public List<MovieLocation> getLocation() {
        return Location;
    }

    public MoviesJson getMovieInfo() {
        return MovieInfo;
    }
}
