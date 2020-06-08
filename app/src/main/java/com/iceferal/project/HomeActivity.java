package com.iceferal.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.iceferal.project.POJO.UserService;
import com.iceferal.project.models.Users;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
    }

    public void runCode(View view){

        UserService userService = UserService.retrofit.create(UserService.class);
        Call <List<Users>> call = userService.getUsers();

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(response.isSuccessful()) {
                    for (Users user:response.body() ) {
                        showPost(user);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) { }
        });
    }

    private void showPost(Users user ) {
        Log.i("userId: ", user.getId() +"\n");
        Log.i("id: " ,user.getLogin()+"\n");
    }


    private void initViews() { }

}