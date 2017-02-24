package com.weasel.penetrate.common.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by dypan on 2017/2/21.
 */
public final class GsonHelper {

    private final static Gson GSON = new GsonBuilder().serializeNulls().create();


    public static String toGson(Object obj){
        return GSON.toJson(obj);
    }

    private GsonHelper(){}
}
