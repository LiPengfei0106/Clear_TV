package com.lipengfei.tv.bean;

import com.lipengfei.tv.global.Variables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee on 2017/5/24.
 */

public class MovieCategoryList {

    public int id;
    public Map<String,String> categoryName;
    public ArrayList<Movie> movies;


    public String getCategoryNameByLanguageCode(String code) {
        if(!categoryName.containsKey(code))
            return categoryName.get(Variables.default_language_code);
        return categoryName.get(code);
    }

}
