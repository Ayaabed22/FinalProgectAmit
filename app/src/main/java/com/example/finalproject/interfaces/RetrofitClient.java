package com.example.finalproject.interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private final ApiInterface myapi;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myapi = retrofit.create(ApiInterface.class);
    }

    public static synchronized RetrofitClient getInstance() {

        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ApiInterface getMyapi() {
        return myapi;
    }

}
