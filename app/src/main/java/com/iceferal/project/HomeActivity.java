package com.iceferal.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.iceferal.project.POJO.UserService;
import com.iceferal.project.models.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void runCode(View view){

        UserService userService = UserService.retrofit.create(UserService.class);
        Call <List<User>> call = userService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()) {
                    for (User user:response.body() ) {
                        showPost(user);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) { }
        });
    }

    private void showPost(User user ) {
        Log.i("userId: ", user.getId() +"\n");
        Log.i("id: " ,user.getLogin()+"\n");
    }

}