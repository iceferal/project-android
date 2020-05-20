package com.iceferal.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_text);
        password = findViewById(R.id.pass_text);

    }
// logowanie
    public void loginClick(View view) {
        boolean status = false;
        String log = login.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(log)) {
            login.setError("Pole haslo nie moze byc puste");
            status = false;        }
        if (TextUtils.isEmpty(pass)) {
            password.setError("Pole haslo nie moze byc puste");
            status = false;        }

        if (status) {

            Toast.makeText(getApplicationContext(), "haslo zostalo zmienione", Toast.LENGTH_LONG).show();
            finish();
        }

    }

//    logowanie fb
    public void fbClick(View view) {
    }

//    logowanie google
    public void googleClick(View view) {
    }
}
