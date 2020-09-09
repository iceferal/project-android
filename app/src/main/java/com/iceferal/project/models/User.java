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
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("login")
    private String login;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

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
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
