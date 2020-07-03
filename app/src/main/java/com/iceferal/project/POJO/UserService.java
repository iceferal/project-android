package com.iceferal.project.POJO;

import com.iceferal.project.models.User;

import org.w3c.dom.Text;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

//    String BASE_URL="http://10.0.2.2:8080/";
    String BASE_URL="https://project-pri.herokuapp.com";

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("/users")
    Call<List<User>> getUsers();

    @GET("/users/{id}")
    Call<User> getUSerId(@Path("id") Long id);

    @GET("users/email/{email}")
    Call<String> checkEmail(@Path("email") String email);

    @GET("users/login/{login}")
    Call<String> checkLogin(@Path("login") String login);

    @POST("/users/create")
    Call<User> postUser(@Body User user);

    @GET("/users/checkit/{checkit}")
    Call<String> checkit(@Path("checkit") String checkit);

}
