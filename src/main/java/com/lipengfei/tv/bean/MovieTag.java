package com.lipengfei.tv.bean;

import java.util.HashMap;

/**
 * Created by Lee on 2017/5/24.
 */

public class MovieTag {

    private String Category;

    private int ID;

    private HashMap<String, String> CategoryName;

    public String getCategory() {
        return Category;
    }

    public int getID() {
        return ID;
    }

    public HashMap<String, String> getCategoryName() {
        return CategoryName;
    }
}
