package com.example.ticketunion.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final RetrofitManager ourInstance = new RetrofitManager();
    private final Retrofit retrofit;

    public static RetrofitManager getInstance(){
        return ourInstance;
    }

    private RetrofitManager(){
        //创建retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(Constasts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
