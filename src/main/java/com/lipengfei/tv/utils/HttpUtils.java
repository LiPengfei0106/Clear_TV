package com.lipengfei.tv.utils;

import android.util.SparseArray;

import com.lipengfei.tv.bean.Channel;
import com.lipengfei.tv.bean.Movie;
import com.lipengfei.tv.bean.MovieCategoryList;
import com.lipengfei.tv.bean.MovieTag;
import com.lipengfei.tv.bean.MoviesJson;
import com.lipengfei.tv.global.Constants;
import com.lipengfei.tv.manager.MovieManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by Lee on 2017/5/24.
 */

public class HttpUtils {

    class Param{
        public String key;
        public String values;
    }

    public static void get(String url,Callback callback){
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(callback);
    }

    public static void post(String url, Callback callback, Param... params){
        PostFormBuilder builder = OkHttpUtils.post().url(url);
        for(Param param : params){
            builder.addParams(param.key,param.values);
        }
        builder.build().execute(callback);
    }

    public abstract static class MovieListCallBack extends Callback {

        public SparseArray<MovieCategoryList> parseNetworkResponse(Response response, int id) throws IOException {
            SparseArray<MovieCategoryList> categorys = new SparseArray<>();

            MoviesJson moviesJson =  JsonUtils.getBeanFromJson(response.body().string(), MoviesJson.class);
            if(moviesJson!=null){
                List<MovieTag> tags = moviesJson.getTags();
                List<Movie> movies = moviesJson.getMovies();
                for(MovieTag tag : tags){
                    MovieCategoryList movieList = new MovieCategoryList();
                    movieList.id = tag.getID();
                    movieList.categoryName = tag.getCategoryName();
                    movieList.movies = new ArrayList<>();

                    categorys.put(movieList.id,movieList);
                }
                for(Movie movie : movies){
                    String tagNameChn = "";
                    String tagNameEng = "";
                    for(Movie.TagsBean bean : movie.getTags()){
                        MovieCategoryList movieList = categorys.get(bean.getTagID());
                        if(movieList!=null){
                            movieList.movies.add(movie);
                            tagNameChn += categorys.get(bean.getTagID()).getCategoryNameByLanguageCode(Constants.ZH_CN) + ",";
                            tagNameEng += categorys.get(bean.getTagID()).getCategoryNameByLanguageCode(Constants.EN_US) + ",";
                        }
                    }
                    try{
                        movie.tagNameMap.put(Constants.ZH_CN, tagNameChn.substring(0, tagNameChn.lastIndexOf(',')));
                        movie.tagNameMap.put(Constants.EN_US, tagNameEng.substring(0, tagNameEng.lastIndexOf(',')));
                    }catch (Exception e){

                    }
                    MovieManager.allMovies.add(movie);
                }

                ArrayList<Integer> keys = new ArrayList<>();
                for(int i = 0;i<categorys.size();i++){
                    int key = categorys.keyAt(i);
                    if(categorys.get(key).movies.size()<1){
                        keys.add(key);
                    }
                }

                for(int key : keys){
                    categorys.remove(key);
                }
            }
            return categorys;
        }
    }

    public abstract static class LiveListCallBack extends Callback {

        public LinkedList<Channel> parseNetworkResponse(Response response, int id) throws IOException {
            LinkedList<Channel> channelList = new LinkedList<>();
            try {
                JSONObject object = new JSONObject(response.body().string());
                JSONArray array = object.getJSONArray("ChannelList");
                for(int i = 0;i<array.length();i++){
                    channelList.add(JsonUtils.getBeanFromJson(array.get(i).toString(),Channel.class));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return channelList;
        }
    }

}
