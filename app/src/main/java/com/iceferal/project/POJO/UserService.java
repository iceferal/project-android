package com.iceferal.project.POJO;

import com.iceferal.project.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    String BASE_URL="http://10.0.2.2:8080/";
    String FEED="users";

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("/users/")
    Call<List<Users>> getUsers();

    @GET("/users/{id{")
    Call<Users> getUSerId(@Path("id") Long id);

    @POST("/users/create/")
    void postUser(@Body Users Body, Callback<Users> Response);


}
