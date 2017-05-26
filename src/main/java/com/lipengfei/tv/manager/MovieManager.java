package com.lipengfei.tv.manager;

import android.util.SparseArray;

import com.lipengfei.tv.bean.Movie;
import com.lipengfei.tv.bean.MovieCategoryList;
import com.lipengfei.tv.global.Variables;
import com.lipengfei.tv.utils.HttpUtils;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import okhttp3.Call;

/**
 * Created by Lee on 2017/5/24.
 */

public class MovieManager {

    public static SparseArray<MovieCategoryList> movieLists;
    public static LinkedHashSet<Movie> relatedMovies = new LinkedHashSet<>();
    public static ArrayList<Movie> allMovies = new ArrayList<>();

    public interface CacheListener{
        void onSuccess(SparseArray<MovieCategoryList> movieLists);
        void onFailed(String msg);
    }

    public static void cacheMovie(final CacheListener listener){
        HttpUtils.get(Variables.movieUrl,new HttpUtils.MovieListCallBack(){

            @Override
            public void onError(Call call, Exception e, int i) {
                listener.onFailed(e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Object o, int i) {

                if(o instanceof SparseArray){
                    movieLists = (SparseArray<MovieCategoryList>) o;
                    listener.onSuccess(movieLists);
                }

            }
        });
    }

    public static void cacheLive(final CacheListener listener){
        HttpUtils.get(Variables.liveUrl,new HttpUtils.LiveListCallBack(){

            @Override
            public void onError(Call call, Exception e, int i) {
                listener.onFailed(e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Object o, int i) {

                if(o instanceof SparseArray){
                    movieLists = (SparseArray<MovieCategoryList>) o;
                    listener.onSuccess(movieLists);
                }

            }
        });
    }
}
