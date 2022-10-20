package com.example.finalproject.interfaces;

import com.example.finalproject.login.loginRequest;
import com.example.finalproject.login.loginResponse;
import com.example.finalproject.model.CartResponse;
import com.example.finalproject.model.dataResponse;
import com.example.finalproject.signup.signUpRequest;
import com.example.finalproject.signup.singUpResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/products")
    Call<ArrayList<dataResponse>>getData();

   @POST("/auth/login")
   Call<loginResponse> getUser(@Body loginRequest loginRequest);

   @GET("/products?sort=desc")
    Call<ArrayList<dataResponse>> sortData();

   @GET("/products/{id}")
    Call<dataResponse> getSingleProduct(@Path("id") int id);

   @GET("/products/category/{category}")
   Call<List<dataResponse>> getCategory(@Path("category")String category);

   @GET("/products/category/{category}")
   Call<ArrayList<dataResponse>> getInCategory(@Path("category") String category);

   @GET("/users/2")
   Call<signUpRequest> getSingleUser();

   @POST("/users")
    Call<singUpResponse> addUser(@Body signUpRequest signUpRequest);

    @GET("/users")
    Call<ArrayList<dataResponse>> getUsers();

    @GET("carts/user/{id}")
    Call<ArrayList<CartResponse>> getUserCarts(@Path("id")int id);


}
