package com.ejajapplication.thewholetruth;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class controller {
    private static final String url = "http://192.168.1.106/thewholetruth/";
    private static controller clientObject;
    private static Retrofit retrofit;

    controller(){
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized controller getInstance()
    {
     if (clientObject==null)
         clientObject= new controller();
     return clientObject;
    }

    apiset getApi(){
        return retrofit.create(apiset.class);
    }

}
