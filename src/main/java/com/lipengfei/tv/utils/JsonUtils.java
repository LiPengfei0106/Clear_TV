package com.lipengfei.tv.utils;

import com.google.gson.Gson;

/**
 * Created by Lee on 2017/5/24.
 */

public class JsonUtils {

    private static Gson gson = new Gson();

    public static <T> T getBeanFromJson(String json,Class<T> cls) {
        try {
            return gson.fromJson(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
