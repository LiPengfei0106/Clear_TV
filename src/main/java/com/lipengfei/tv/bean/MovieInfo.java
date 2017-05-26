package com.lipengfei.tv.bean;

/**
 * Created by Lee on 2017/5/24.
 */

public class MovieInfo {


    /**
     * MoviesFilter : null
     * FeeDiscount : 1
     * MovieContentAPIURL : http://vodresource.cleartv.cn/backend_movie/v1/jsoncontent
     * ViewID : 30
     * MovieContentAPIParam : Default
     * PackageFee : 1
     * ID : 4
     */

    private String MoviesFilter;
    private int FeeDiscount;
    private String MovieContentAPIURL;
    private int ViewID;
    private String MovieContentAPIParam;
    private int PackageFee;
    private int ID;

    public String getMoviesFilter() {
        return MoviesFilter;
    }

    public void setMoviesFilter(String MoviesFilter) {
        this.MoviesFilter = MoviesFilter;
    }

    public int getFeeDiscount() {
        return FeeDiscount;
    }

    public void setFeeDiscount(int FeeDiscount) {
        this.FeeDiscount = FeeDiscount;
    }

    public String getMovieContentAPIURL() {
        return MovieContentAPIURL;
    }

    public void setMovieContentAPIURL(String MovieContentAPIURL) {
        this.MovieContentAPIURL = MovieContentAPIURL;
    }

    public int getViewID() {
        return ViewID;
    }

    public void setViewID(int ViewID) {
        this.ViewID = ViewID;
    }

    public String getMovieContentAPIParam() {
        return MovieContentAPIParam;
    }

    public void setMovieContentAPIParam(String MovieContentAPIParam) {
        this.MovieContentAPIParam = MovieContentAPIParam;
    }

    public int getPackageFee() {
        return PackageFee;
    }

    public void setPackageFee(int PackageFee) {
        this.PackageFee = PackageFee;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
