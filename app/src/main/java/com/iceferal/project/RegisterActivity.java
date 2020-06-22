package com.iceferal.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iceferal.project.POJO.UserService;
import com.iceferal.project.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText login, email, pass, repass;
    UserService userService = UserService.retrofit.create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login = findViewById(R.id.login);
        pass = findViewById(R.id.pass);
        repass = findViewById(R.id.repassword);
        email = findViewById(R.id.email);
    }

    public void klik(View view) {
        boolean status = true;
        String log = login.getText().toString();
        String password = pass.getText().toString();
        String repassword = repass.getText().toString();
        String mail = email.getText().toString();

        if (TextUtils.isEmpty(log)) {
            login.setError("Pole login nie moze byc puste");
            status = false;        }
        if (TextUtils.isEmpty(password)) {
            pass.setError("Pole haslo nie moze byc puste");
            status = false;         }
        if (TextUtils.isEmpty(repassword)) {
            repass.setError("Pole haslo nie moze byc puste");
            status = false;        }
        if (!password.equals(repassword)) {
            repass.setError("Hasla roznia sie od siebie");
            status = false;        }
        if (TextUtils.isEmpty(mail)) {
            email.setError("Pole e-mail nie moze byc puste");
            status = false;         }

        Call<String> loginCheck = userService.checkLogin(log);
        loginCheck.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String loginCheck = response.body();
                if(loginCheck == "true") {
                    login.setError("ten login jest już w użyciu");                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Serwer chwilowo nie odpowiada, spróbuj pozniej.", Toast.LENGTH_SHORT).show();            }
        });


        Call<String> mailCheck = userService.checkEmail(mail);
        mailCheck.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String mailCheck = response.body();
                if(mailCheck == "true") {
                    email.setError("ten e-mail jest juz w uzyciu");                 }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Serwer chwilowo nie odpowiada, spróbuj pozniej.", Toast.LENGTH_SHORT).show();              }
        });

        if(status) {
            User user = new User("null", "null", log, mail, password);
            Call<User> userCall = userService.postUser(user);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    String resCode = String.valueOf(response.code());
                    if(resCode.equals("200")) {
                        Toast.makeText(RegisterActivity.this, "Konto zostalo utworzone!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    if(resCode.equals("500")) {
                        Toast.makeText(RegisterActivity.this, "Blad przy tworzeniu konta.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Serwer chwilowo nie odpowiada, spróbuj pozniej.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

    }
}
