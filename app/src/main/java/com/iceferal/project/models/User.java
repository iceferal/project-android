package com.iceferal.project.models;

import android.content.Intent;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.iceferal.project.HomeActivity;
import com.iceferal.project.LoginActivity;
import com.iceferal.project.POJO.UserService;
import com.iceferal.project.RegisterActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User {

    @SerializedName("id")
    private static Long id;
    @SerializedName("name")
    private static String name;
    @SerializedName("surname")
    private static String surname;
    @SerializedName("login")
    private String login;
    @SerializedName("email")
    private static String email;
    @SerializedName("password")
    private String password;
    @SerializedName("image")
    private static String image;

    public User(Long id, String name, String surname, String login, String email, String password) {
        this.id = this.id;
        this.name = name;
        this.surname  = surname;
        this.login = login;
        this.email = email;
        this.password = password;
    }


    public Long getId() {
        return id;
    }
    public static void setId(Long id) {
        User.id = id;
    }

    public static String getName() {
        return name;
    }
    public static void setName(String name) {
        User.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public static void setSurname(String surname) {
        User.surname = surname;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) {
        this.login = login;
    }

    public static String getEmail() {
        return email;
    }
    public static void setEmail(String email) {
        User.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static String getImage() {
        return image;
    }
    public static void setImage(String image) {
        User.image = image;
    }
}
